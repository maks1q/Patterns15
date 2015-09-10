#include "unit.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
Unit::Unit()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param Armor		броня
/// \param RatioArmor	коэффициент поглощения урона(коэффициент защиты)
Unit::Unit(int Armor, f32 RatioArmor)
{
	this->Armor			= Armor		;
	this->RatioArmor	= RatioArmor;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
Unit::~Unit()
{
	vecPoint.clear ();
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить значение брони
/// \param Armor значение брони
void Unit::setArmor (int Armor)
{
	this->Armor = Armor;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить значение коэффициента брони
/// \param RatioArmor значение коэффициента брони
void Unit::setRatioArmor (f32 RatioArmor)
{
	this->RatioArmor = RatioArmor;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить значение скорости юнита
/// \param Speed скорость движения юнита
void Unit::setSpeed (f32 Speed)
{
	this->Speed = Speed;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить данные о юните
/// \param Armor			броня
/// \param RatioArmor		коэффициент защиты
/// \param Speed			скорость движения
void Unit::setInfoUnit(int Armor, f32 RatioArmor, f32 Speed)
{
	setArmor (Armor);
	setRatioArmor (RatioArmor);
	setSpeed (Speed);
	MyState.setState (State::IIDLE);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить значение брони
/// \return Значение брони
int Unit::getArmor ()
{
	return Armor;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить значение коэффициента брони
/// \return Значение коэффициента брони
f32 Unit::getRatioArmor ()
{
	return RatioArmor;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить значение скорости юнита
/// \return Значение скорости движения юнита
f32 Unit::getSpeed ()
{
	return Speed;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить угол поворота к объекту
/// \param base	главные координаты
/// \param target целевые координаты
/// \return	Угол поворота к объекту
f32 Unit::getRotateAngle(vector3df base, vector3df target)
{
	vector3df direction = target - base;
	return (   -atan2(direction.Z, direction.X) * irr::core::RADTODEG     );
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция вывода информации о объекте
void Unit::toShowInfo ()
{
	Object::toShowInfo ();
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Метод движения юнита
void Unit::toMove ()
{
	if(vecPoint.size ())
	{
		vector3df Point = vecPoint[0];
		toMoveToPoint (Point);
		if(MyState.getState () == State::IIDLE)
			vecPoint.erase (vecPoint.begin ());
	}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Метод движения юнита к точке
/// \param Point место назначения
void Unit::toMoveToPoint (vector3df Point)
{
	core::vector3df nodePosition = sceneNode->getPosition();
	core::vector3df Way;
	f32 Radius = 1.0;

	// Получаем вектор направления и нормализуем его
	Way.X = Point.X - nodePosition.X;
	Way.Y = Point.Y - nodePosition.Y;
	Way.Z = Point.Z - nodePosition.Z;
	Way.normalize ();

	// Поворачиваемся к цели
	toTurn (Point);

	// Движемся к цели по новому вектору
	nodePosition.X += Way.X * Speed;
	nodePosition.Z += Way.Z * Speed;

	// Приблизились -> достигли места
	if( fabsf (nodePosition.X - Point.X) < Radius && fabsf (nodePosition.Z - Point.Z) < Radius)
	{
		nodePosition.X=Point.X;
		nodePosition.Z=Point.Z;
	}

	// Передвигаемся на новую позицию
	sceneNode->setPosition (nodePosition);

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Метод движения к ноде
/// \param targetNode целевая нода, к которой необходимо двигаться
void Unit::toMoveToNode(IMeshSceneNode *targetNode)
{
	toMoveToPoint (targetNode->getPosition ());
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Метод поворота к точке
/// \param Point пункт назначения
void Unit::toTurn (vector3df Point)
{
	// Резкий поворот
	core::vector3df newDir = Point - sceneNode->getPosition();
	newDir.normalize();
	newDir.Y = sceneNode->getPosition ().Y;

	sceneNode->setRotation( newDir.getHorizontalAngle () );

	// Плавный поворот

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Проверка на достижения места назначения
/// \param Point место назначения
/// \return true, если достигли, false, если не достигли
bool Unit::isIArrived(vector3df Point)
{
	core::vector3df nodePosition = sceneNode->getPosition();

	if(nodePosition.X==Point.X && nodePosition.Z==Point.Z)
	{
		return true;
	}

	return false;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Проверка расстояния до цели
/// \param Point	координаты цели
/// \param Radius	расстояние достаточного приближения к цели
/// \return true, если расстояние достаточное, false, если расстояние недостаточное
bool Unit::isTargetInRadius(vector3df Point, f32 Radius)
{
	core::vector3df nodePosition = sceneNode->getPosition();

	if(nodePosition.getDistanceFrom (Point) <= Radius)
	{
		return true;
	}

	return false;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Проверка указателя
/// \return true, если != NULL, false, если == NULL
bool Unit::isCheckTheIndex()
{
	return false;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Проверка указателя по всем юнитам
/// \param Units контейнер указателей на всех юнитов
/// \return	true, если != NULL, false, если == NULL
bool Unit::isCheckTheIndex(std::vector<Unit *> Units)
{
	return false;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Проверка указателя по всем спайсовым слоям
/// \param MSpice карта спайса
/// \return	true, если != NULL, false, если == NULL
bool Unit::isCheckTheIndex(std::vector<LayerSpice *> MSpice)
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Добавить место назначения
/// \param Point	место назначения
void Unit::addPoint (vector3df Point)
{
	vecPoint.push_back (Point);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновить действия юнита
void Unit::Update()
{
	if(getArmor () <=0)
		MyState.setState(State::IEXPLODE);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновить действия юнита
/// \param Maps глобальная карта
void Unit::Update(Map *Maps)
{

}


