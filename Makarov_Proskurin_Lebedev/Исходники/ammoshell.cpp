#include "ammoshell.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
AmmoShell::AmmoShell()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param position	позиция модели объекта при создании
/// \param IDTEAM	идентификационный номер команды
/// \param targetPosition	координаты цели
AmmoShell::AmmoShell(irr::video::IVideoDriver *driver,
					 irr::scene::ISceneManager *smgr,
					 irr::core::vector3df position,
					 IDTeam IDTEAM,
					 irr::core::vector3df targetPosition)
{
	setInfo ("Снаряд", 300, 1, IDTEAM);
	setInfoModel (driver, smgr, "Textures/metal_8.jpg", position);
	setInfoAmmo (100, 75);
	TargetPosition = targetPosition;
	MyState.setState (State::IGOTOATTACK);

	sceneNode->setScale (vector3df(0.5,0.5,0.5));
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
AmmoShell::~AmmoShell()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновление действий для снаряда
void AmmoShell::Update()
{
	Ammunition::Update ();
}
