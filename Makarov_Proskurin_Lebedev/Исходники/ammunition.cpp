#include "ammunition.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструкто по умолчанию
Ammunition::Ammunition()
{
	MyState.setState (State::IGOTOATTACK);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver			драйвер устройства
/// \param smgr				менеджер сцены
/// \param position			позиция модели объекта при создании
/// \param IDTEAM			идентификационный номер команды
/// \param targetPosition	координаты цели
Ammunition::Ammunition(irr::video::IVideoDriver *driver,
					   irr::scene::ISceneManager *smgr,
					   irr::core::vector3df position,
					   IDTeam IDTEAM,
					   irr::core::vector3df targetPosition)
{
	setInfo ("Боеприпасы", 300, 0, IDTEAM);
	setInfoAmmo (100, 1);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
Ammunition::~Ammunition()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить наносимое повреждение
/// \param Damage наносимое повреждение
void Ammunition::setDamage(int Damage)
{
	this->Damage = Damage;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить радиус поражения
/// \param RadiusOfDefeat радиус поражения
void Ammunition::setRadiusOfDefeat(f32 RadiusOfDefeat)
{
	this->RadiusOfDefeat = RadiusOfDefeat;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить координаты цели
/// \param TargetPosition координаты цели
void Ammunition::setTargetPosition(vector3df TargetPosition)
{
	this->TargetPosition = TargetPosition;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить наносимое повреждение
/// \return Наносимое повреждение
int Ammunition::getDamage()
{
	return this->Damage;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить радиус поражения
/// \return Радиус поражения
f32 Ammunition::getRadiusOfDefeat()
{
	return this->RadiusOfDefeat;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить координаты цели
/// \return Координаты цели
vector3df Ammunition::getTargetPosition()
{
	return this->TargetPosition;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить данные о боеприпасах
/// \param Damage			наносимое повреждение
/// \param RadiusOfDefeat	радиус поражения
void Ammunition::setInfoAmmo(int Damage, f32 RadiusOfDefeat)
{
	setDamage (Damage);
	setRadiusOfDefeat (RadiusOfDefeat);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Движение к цели
void Ammunition::toMoveToTarget ()
{
	core::vector3df nodePosition = sceneNode->getPosition();
	core::vector3df Point = TargetPosition;
	core::vector3df Way;

	// Получаем вектор направления и нормализуем его
	Way.X = Point.X - nodePosition.X;
	Way.Y = Point.Y - nodePosition.Y;
	Way.Z = Point.Z - nodePosition.Z;
	Way.normalize ();

	// Движемся к цели по новому вектору
	nodePosition.X += Way.X *20;
	nodePosition.Y += Way.Y *20;
	nodePosition.Z += Way.Z *20;

	if(nodePosition.getDistanceFrom (Point) <= getRadiusOfDefeat ())
		MyState.setState (State::IEXPLODE);

	// Передвигаемся на новую позицию
	sceneNode->setPosition (nodePosition);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновление действий для боеприпаса
void Ammunition::Update()
{
	switch (MyState.getState ())
	{
		case State::IGOTOATTACK :
		{
			toMoveToTarget ();
			break;
		}
		case State::IEXPLODE	:
		{
			this->~Ammunition ();
			break;
		}
		default :
		{
			break;
		}
	}
}
