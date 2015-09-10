#include "constructionplant.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
ConstructionPlant::ConstructionPlant()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver		драйвер устройства
/// \param smgr			менеджер сцены
/// \param position		позиция модели объекта при создании
/// \param IDTEAM		идентификационный номер команды
/// \param SpiceTeam	указатель на командный склад спайса
ConstructionPlant::ConstructionPlant(irr::video::IVideoDriver    *   driver,
									 irr::scene::ISceneManager   *   smgr,
									 irr::core::vector3df position,
									 IDTeam IDTEAM,
									 Spice *SpiceTeam)
{
	setInfo ("Строительный завод", 200, 1, IDTEAM);
	switch(IDTEAM) {
		case ATREIDES:
			setInfoModel (driver, smgr, "Textures/metal_atreides.jpg", position);
			break;
		case ORDOS:
			setInfoModel (driver, smgr, "Textures/metal_ordos.jpg", position);
			break;
		case HARKONNEN:
			setInfoModel (driver, smgr, "Textures/metal_harkonnen.jpg", position);
			break;
		default:
			break;
	}
	//ConstructionPlant::setInfoModel(driver,smgr,"Textures/concrete_14.jpg", position);
	this->SpiceTeam = SpiceTeam;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
ConstructionPlant::~ConstructionPlant ()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Инициализация модели строительного завода
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param texture	текстура модели
/// \param position	позиция модели объекта при создании
void ConstructionPlant::setInfoModel (IVideoDriver *driver, ISceneManager *smgr, std::string texture, irr::core::vector3df position)
{
	tex = driver->getTexture(/*path+*/texture.c_str ());

	sceneNode = smgr->addCubeSceneNode ();
	sceneNode->setMaterialTexture(0, tex);
//	sceneNode->setMaterialFlag(video::EMF_LIGHTING, false);
	sceneNode->setMaterialFlag(video::EMF_LIGHTING, true);
	sceneNode->setScale (vector3df(5,5,5));
	sceneNode->setPosition (position);
//	sceneNode->setDebugDataVisible (1);
	irr::video::ITexture * tex2	= driver->getTexture("Textures/metal_8.jpg");

	ISceneNode * cube;
	cube = smgr->addCubeSceneNode (10.0,0,-1,sceneNode->getPosition () + vector3df(-50,-25,0),vector3df(0,0,0),vector3df(5,0,5));
	cube->setMaterialTexture(0, tex2);
	cube->setMaterialFlag(video::EMF_LIGHTING, true);
//	cube->setDebugDataVisible (1);

	cube = smgr->addCubeSceneNode (10.0,0,-1,sceneNode->getPosition () + vector3df(-50,-25,50),vector3df(0,0,0),vector3df(5,0,5));
	cube->setMaterialTexture(0, tex2);
	cube->setMaterialFlag(video::EMF_LIGHTING, true);
//	cube->setDebugDataVisible (1);

	cube = smgr->addCubeSceneNode (10.0,0,-1,sceneNode->getPosition () + vector3df(-50,-25,-50),vector3df(0,0,0),vector3df(5,0,5));
	cube->setMaterialTexture(0, tex2);
	cube->setMaterialFlag(video::EMF_LIGHTING, true);
//	cube->setDebugDataVisible (1);

	cube = smgr->addCubeSceneNode (10.0,0,-1,sceneNode->getPosition () + vector3df(50,-25,0),vector3df(0,0,0),vector3df(5,0,5));
	cube->setMaterialTexture(0, tex2);
	cube->setMaterialFlag(video::EMF_LIGHTING, true);
//	cube->setDebugDataVisible (1);

	cube = smgr->addCubeSceneNode (10.0,0,-1,sceneNode->getPosition () + vector3df(50,-25,50),vector3df(0,0,0),vector3df(5,0,5));
	cube->setMaterialTexture(0, tex2);
	cube->setMaterialFlag(video::EMF_LIGHTING, true);
//	cube->setDebugDataVisible (1);

	cube = smgr->addCubeSceneNode (10.0,0,-1,sceneNode->getPosition () + vector3df(50,-25,-50),vector3df(0,0,0),vector3df(5,0,5));
	cube->setMaterialTexture(0, tex2);
	cube->setMaterialFlag(video::EMF_LIGHTING, true);
//	cube->setDebugDataVisible (1);

	cube = smgr->addCubeSceneNode (10.0,0,-1,sceneNode->getPosition () + vector3df(0,-25,50),vector3df(0,0,0),vector3df(5,0,5));
	cube->setMaterialTexture(0, tex2);
	cube->setMaterialFlag(video::EMF_LIGHTING, true);
//	cube->setDebugDataVisible (1);

	cube = smgr->addCubeSceneNode (10.0,0,-1,sceneNode->getPosition () + vector3df(0,-25,-50),vector3df(0,0,0),vector3df(5,0,5));
	cube->setMaterialTexture(0, tex2);
	cube->setMaterialFlag(video::EMF_LIGHTING, true);
//	cube->setDebugDataVisible (1);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Создание Харвестера строительным зданием
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \return	0, если не удалось создать объект или указатель на созданный объект
Harvester *ConstructionPlant::createHarv(IVideoDriver *driver, ISceneManager *smgr)
{
	if(SpiceTeam->getRes () >= 600)
	{
		SpiceTeam->minusRes (600);
		Harvester * Harv = new Harvester(driver,smgr,sceneNode->getPosition ()-vector3df(0,5,75),IDTEAM,SpiceTeam);
		Harv->Home = sceneNode->getPosition ();
		return Harv;
	}
	else
	{
		return 0;
	}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Создание Танка строительным зданием
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \return 0, если не удалось создать объект или указатель на созданный объект
Tank *ConstructionPlant::createTank(IVideoDriver *driver, ISceneManager *smgr)
{
	if(SpiceTeam->getRes () >= 800)
	{
		SpiceTeam->minusRes (800);
		Tank * Tan = new Tank(driver,smgr,sceneNode->getPosition ()-vector3df(75,5,0),IDTEAM);
		return Tan;
	}
	else
	{
		return 0;
	}
}
