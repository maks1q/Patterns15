#include "combatunit.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
CombatUnit::CombatUnit()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
CombatUnit::~CombatUnit()
{
	TargetUnit = NULL;
}

//////////////////////////////////////////////////////////////////////////////////
///// \brief Метод атаки объекта
///// \param driver		драйвер устройства
///// \param smgr		менеджер сцены
///// \param sceneNode	объект для нападения
//void CombatUnit::Attack (ISceneManager *smgr, IVideoDriver *driver, IMeshSceneNode *sceneNode)
//{

//}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция выстрела орудием
/// \param driver		драйвер устройства
/// \param smgr			менеджер сцены
/// \param sceneNode	объект для нападения
/// \param Ammo			контейнер указателей на все боеприпасы
void CombatUnit::toAttack (IVideoDriver *driver, ISceneManager *smgr, IMeshSceneNode *sceneNode, std::vector<Ammunition *> *Ammo)
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Поиск вражеского юнита
/// \param Units контейнер указателей на всех юнитов
void CombatUnit::toSearchOfEnemy(std::vector<Unit*> Units)
{
	int number = -1;
	float length =  0;

//	if(isCheckTheIndex (Units))
//		return;

	// Ищем ближайшего вражеского юнита
	for(int i=0; i<Units.size(); i++)
	{
		if(Units[i]->getIDTeam() != getIDTeam ())
		{
			vector3df targetPosition = Units[i]->sceneNode->getPosition();
			vector3df nodePosition = sceneNode->getPosition ();

			float len=nodePosition.getDistanceFrom (targetPosition);

			if(	len < length || !i || number == -1)
			{
				number = i;
				length = len;
			}
		}
	}

	if(number == -1)															// Если не нашли, то возвращаемся на базу
	{
		MyState.setState (State::ICANNOTENEMYUNIT);
		TargetUnit = NULL;
	}
	else																		// Если нашли, то идем атаковать
	{
		TargetUnit = Units[number];
		MyState.setState (State::IGOTOATTACK);
	}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновить действия боевого юнита
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param Maps		глобальная карта
/// \param Units	контейнер указателей на всех юнитов
/// \param Ammo		контейнер указателей на все боеприпасы
void CombatUnit::Update(IVideoDriver *driver,
						ISceneManager *smgr,
						Map *Maps, std::vector<Unit*> Units,
						std::vector<Ammunition *> *Ammo)
{
	Unit::Update ();

	switch (MyState.getState ())
	{
		case State::IIDLE			:											// Состояние бездействия
		{
			toSearchOfEnemy (Units);
			MyState.setState (State::ISEARCHENEMY);
			break;
		}
		case State::IEXPECT			:											// Ожидание
		{
			break;
		}
		case State::IATTACK			:											// Атака цели
		{
			toAttack (driver, smgr, TargetUnit->sceneNode, Ammo);
			toSearchOfEnemy (Units);
			break;
		}
		case State::IGOTOATTACK		:											// Движение в направлении цели
		{
			if(isCheckTheIndex (Units))
			{
				toSearchOfEnemy (Units);

				if(isTargetInRadius (TargetUnit->sceneNode->getPosition (), getAttackDistance ()))
				{
					toAttack (driver, smgr, TargetUnit->sceneNode, Ammo);
					MyState.setState (State::IAMRECHARGED);

				}
				else
				{
					toMoveToPoint (TargetUnit->sceneNode->getPosition ());
				}
			}
			else
			{
				MyState.setState (State::ISEARCHENEMY);
				TargetUnit = NULL;
			}

			break;
		}
		case State::IBACK			:											// Отступление
		{

			break;
		}
		case State::ISEARCHENEMY	:											// Поиск вражеских юнитов
		{
			toSearchOfEnemy (Units);
			if(TargetUnit)
			{
				MyState.setState (State::IGOTOATTACK);
			}
			break;
		}
		case State::ICANNOTENEMYUNIT:											// Невозможность найти вражеского юнита
		{
			toSearchOfEnemy (Units);
			break;
		}

		case State::IAMRECHARGED	:
		{
			toSearchOfEnemy (Units);
			if(getRechargeTime ())
			{
				setRechargeTime (getRechargeTime ()-1);
			}
			else
			{
				setRechargeTime (500);
				MyState.setState (State::IGOTOATTACK);
			}

			break;
		}
		case State::IEXPLODE		:
		{
			this->~CombatUnit ();
			break;
		}
		default:
		{
			break;
		}
	}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Проверка указателя на юнита
/// \param Units контейнер указателей на всех юнитов
/// \return true, если юнит еще существует, false, если юнит уже не существует
bool CombatUnit::isCheckTheIndex(std::vector<Unit*> Units)
{
	// Проверяем на существование указатель на прошлый спайсовый слой
	for(unsigned int i=0; i<Units.size(); i++)
	{
		if(TargetUnit == Units[i])
		{
			MyState.setState (State::IGOTOATTACK);
			return true;
		}
	}

	return false;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить значение расстояния нападения
/// \param AttackDistance расстояние для атаки
void CombatUnit::setAttackDistance (float AttackDistance)
{
	if(AttackDistance <=0)
	{
		this->AttackDistance = 0;
	}
	else
	{
		this->AttackDistance = AttackDistance;
	}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить время перезарядки
/// \param RechargeTime время перезарядки
void CombatUnit::setRechargeTime(int RechargeTime)
{
	if(RechargeTime <=0)
	{
		this->RechargeTime = 0;
	}
	else
	{
		this->RechargeTime = RechargeTime;
	}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить информацию о боевом юните
/// \param AttackDistance расстояние для атаки
/// \param RechargeTime время перезарядки
void CombatUnit::setInfoCombUnit(float AttackDistance, int RechargeTime)
{
	setAttackDistance (AttackDistance);
	setRechargeTime (RechargeTime);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief  Получить значение расстояния нападения
/// \return Расстояние для нападения
float CombatUnit::getAttackDistance ()
{
	return AttackDistance;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить время перезарядки
/// \return Время перезарядки
int CombatUnit::getRechargeTime()
{
	return RechargeTime;
}
