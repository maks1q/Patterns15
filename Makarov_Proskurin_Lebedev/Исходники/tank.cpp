#include "tank.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
Tank::Tank()
{
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param position	позиция модели объекта при создании
/// \param IDTEAM	идентификационный номер команды
Tank::Tank(irr::video::IVideoDriver *driver,
		   irr::scene::ISceneManager *smgr,
		   irr::core::vector3df position,
		   IDTeam IDTEAM)
{
	setInfo ("Tank", 200, 3, IDTEAM);
	setInfoUnit (1000, 0.5, 10);
	setInfoCombUnit (300, 50000);

	switch(IDTEAM) {
		case ATREIDES:
			setInfoModel (driver, smgr, "Textures/tank_atreides.jpg", position);
			break;
		case ORDOS:
			setInfoModel (driver, smgr, "Textures/tank_ordos.jpg", position);
			break;
		case HARKONNEN:
			setInfoModel (driver, smgr, "Textures/tank_harkonnen.jpg", position);
			break;
		default:
			break;
	}

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
Tank::~Tank()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Инициализация модели танка
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param texture	текстура модели
/// \param position позиция модели при создании
void Tank::setInfoModel(IVideoDriver *driver, ISceneManager *smgr, std::string texture, vector3df position)
{
	tex = driver->getTexture(texture.c_str ());

	sceneNode = smgr->addCubeSceneNode ();
	sceneNode->setMaterialTexture(0, tex);
//	sceneNode->setMaterialFlag(video::EMF_LIGHTING, false);
	sceneNode->setMaterialFlag(video::EMF_LIGHTING, true);
	sceneNode->setScale (vector3df(2,4,2));
	sceneNode->setPosition ( position );

//	sceneNode->setDebugDataVisible (1);

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Атаковать объект
/// \param driver		драйвер устройства
/// \param smgr			менеджер сцены
/// \param targetNode	объект для нападения
/// \param Ammo			контейнер указателей на все боеприпасы
void Tank::toAttack(IVideoDriver *driver,
					ISceneManager *smgr,
					IMeshSceneNode *targetNode,
					std::vector<Ammunition *> * Ammo)
{
	toTurn (targetNode->getPosition ());
	AmmoShell *AShell = new AmmoShell(driver, smgr, sceneNode->getPosition (), getIDTeam (), targetNode->getPosition ());
	Ammo->push_back (AShell);

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновление действий танка
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param Maps		глобальная карта
/// \param Units	контейнер указателей на всех юнитов
/// \param Ammo		контейнер указателей на все боеприпасы
void Tank::Update(IVideoDriver *driver,
				  ISceneManager *smgr,
				  Map *Maps,
				  std::vector<Unit*> Units,
				  std::vector<Ammunition *> *Ammo)
{
	CombatUnit::Update (driver, smgr, Maps, Units, Ammo);
}
