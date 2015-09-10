#include "extractingunit.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
ExtractingUnit::ExtractingUnit()
{
	setProductionSpeed (100);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
ExtractingUnit::~ExtractingUnit()
{
	ThisSpice = NULL;
	SpiceTeam = NULL;

	SpiceBunker->~Spice ();
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить значение скорости добычи ресурсов
/// \param ProductionSpeed значение скорости добычи ресурсов
void ExtractingUnit::setProductionSpeed(unsigned int ProductionSpeed)
{
	this->ProductionSpeed = ProductionSpeed;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить данные о добывающем юните
/// \param ProductionSpeed скорость добычи ресурса
/// \param Res			текущее количество ресурса в отсеке добывающего юнита
/// \param MaxRes		максимальное количество ресурса, перевозимое добывающим юнитом
/// \param SpiceTeam	указатель на командный склад спайса
void ExtractingUnit::setInfoExUnit(unsigned int ProductionSpeed, int Res, int MaxRes, Spice *SpiceTeam)
{
	setProductionSpeed (ProductionSpeed);
	SpiceBunker = new Spice(Res, MaxRes, IDTEAM);
	this->SpiceTeam = SpiceTeam;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить значение скорости добычи ресурсов
/// \return Значение скорости добычи ресурсов
unsigned int ExtractingUnit::getProductionSpeed() const
{
	return ProductionSpeed;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция для поиска ресурсов
/// \param MSpice карта спайса
void ExtractingUnit::toSearchOfResources(std::vector<LayerSpice*> MSpice)
{
	int number = -1;
	f32 length =  0;

	if(isCheckTheIndex (MSpice))
		return;

	// Ищем ближайший спайсовый слой
	for(int i=0; i<MSpice.size(); i++)
	{
		if(!MSpice[i]->isFree && i)
			continue;
		vector3df targetPosition = MSpice[i]->sceneNode->getPosition();
		vector3df nodePosition = sceneNode->getPosition ();

		f32 len=nodePosition.getDistanceFrom (targetPosition);
		if(	len < length || !i)
		{
			number = i;
			length = len;
		}
	}


	if(number == -1)															// Если не нашли, то возвращаемся на базу
	{
		MyState.setState (State::ICANNOTFINDSPICE);
		ThisSpice = NULL;
	}
	else																		// Если нашли, то идем добывать ресурс
	{
		if(MSpice.size ()==1)
		{
			if(MSpice[0]->isFree)
			{
				ThisSpice = MSpice[0];
				ThisSpice->isFree = false;
				MyState.setState (State::IGOTOGETSPICE);
			}
			else
			{
				MyState.setState (State::ICANNOTFINDSPICE);
			}
		}
		else
		{
			ThisSpice = MSpice[number];
			ThisSpice->isFree = false;
			MyState.setState (State::IGOTOGETSPICE);
		}

	}

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция для добычи ресурсов
/// \param ThisLayerSpice текущий слой спайса
void ExtractingUnit::toExtractSpice(LayerSpice *ThisLayerSpice)
{
	SpiceBunker->plusRes (ThisLayerSpice->SpiceBox->minusRes (ProductionSpeed));
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция для обновления действия добывающего юнита
void ExtractingUnit::Update()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция для обновления действия добывающего юнита
/// \param Maps	глобальная карта
void ExtractingUnit::Update(Map * Maps)
{
	Unit::Update ();

	switch (MyState.getState ())
	{
		case State::IIDLE		:												// Состояние бездействия
		{
			if( !SpiceBunker->isFull () )
			{
				ExtractingUnit::toSearchOfResources (Maps->IMapSpice->MapLayerSpice);
			}
			else
			{
				MyState.setState (State::ICOMEBACK);
			}
			break;
		}

		case State::IEXPECT				:										// Ожидание
		{
			break;
		}

		case State::IGETRES				:										// Добыча ресурса
		{
			if( isCheckTheIndex (Maps->IMapSpice->MapLayerSpice) )
			{
				ThisSpice->isFree = false;
				ExtractingUnit::toExtractSpice (ThisSpice);

				if(SpiceBunker->isFull ())
				{
					ThisSpice->isFree = true;
					MyState.setState (State::ICOMEBACK);
				}

				if(ThisSpice->SpiceBox->emptyRes ())
				{
					ThisSpice = NULL;
				}
			}
			else
			{
				MyState.setState (State::IIDLE);
			}

			break;
		}

		case State::IGIVERES			:										// Передача ресурсов
		{
			if( SpiceTeam->isFull () )
			{
				MyState.setState (State::IIDLE);
			}
			else
			{
				if(SpiceBunker->emptyRes ())
				{
					MyState.setState (State::IIDLE);
					ThisSpice = NULL;
					break;
				}
				SpiceTeam->plusRes (SpiceBunker->minusRes (ProductionSpeed));
			}
			break;
		}

		case State::ICOMEBACK			:										// Возвращение на базу с целью передачи ресурсов
		{
			if(isTargetInRadius (Home,75))
			{
				MyState.setState (State::IGIVERES);
			}
			else
			{
				toMoveToPoint (Home);
			}

			break;
		}

		case State::IBACK				:										// Отступление
		{
			break;
		}

		case State::ICANNOTFINDSPICE	:										// Невозможность найти ресурсы
		{
			MyState.setState (State::ICOMEBACK);
			break;
		}

		case State::IGOTOGETSPICE		:										// Движение к ресурсам
		{
			if( isCheckTheIndex (Maps->IMapSpice->MapLayerSpice) )
			{
				if( isIArrived (ThisSpice->sceneNode->getPosition ()) )
				{
					MyState.setState (State::IGETRES);
				}
				else
				{
					toMoveToPoint (ThisSpice->sceneNode->getPosition ());
				}
			}
			else
			{
				MyState.setState (State::IIDLE);
			}
			break;
		}
		case State::IEXPLODE		:
		{
			this->~ExtractingUnit ();
			break;
		}
		default:
		{
			break;
		}
	}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Проверка указателя по всем спайсовым слоям
/// \param MSpice карта спайса
/// \return	true, если != NULL, false, если == NULL
bool ExtractingUnit::isCheckTheIndex(std::vector<LayerSpice *> MSpice)
{
	// Проверяем на существование указатель на прошлый спайсовый слой
	for(int i=0; i<MSpice.size(); i++)
	{
		if(ThisSpice == MSpice[i])
		{
			MyState.setState (State::IGOTOGETSPICE);
			return true;
		}
	}

	return false;
}


