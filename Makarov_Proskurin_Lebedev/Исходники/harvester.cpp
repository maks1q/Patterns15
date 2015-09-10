#include "harvester.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
Harvester::Harvester()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param position	позиция модели объекта при создании
/// \param IDTEAM	идентификационный номер команды
/// \param SpiceTeam указатель на спайсовый склад команды
Harvester::Harvester(irr::video::IVideoDriver    *   driver,
					 irr::scene::ISceneManager   *   smgr,
					 irr::core::vector3df position,
					 IDTeam IDTEAM,
					 Spice *SpiceTeam)
{
	setInfo ("Harvester",200,2,IDTEAM);
	setInfoUnit (500, 0.2, 10);
	setInfoModel(driver,smgr,"Textures/metal_11.jpg", position);
	setInfoExUnit (100, 0, 700, SpiceTeam);

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
Harvester::~Harvester()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция инициализации модели Харвестера
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param texture	текстура модели
/// \param position	позиция модели объекта при создании
void Harvester::setInfoModel (IVideoDriver *driver, ISceneManager *smgr, std::string texture, irr::core::vector3df position)
{
	tex = driver->getTexture(texture.c_str ());

	sceneNode = smgr->addCubeSceneNode ();
	sceneNode->setMaterialTexture(0, tex);
//	sceneNode->setMaterialFlag(video::EMF_LIGHTING, false);
//	sceneNode->setMaterialFlag(EMF_NORMALIZE_NORMALS,true);
	sceneNode->setMaterialFlag(EMF_LIGHTING,true);
//	sceneNode->setMaterialFlag(EMF_GOURAUD_SHADING,true);
	sceneNode->setScale (vector3df(2,5,2));
	sceneNode->setPosition (position);
	sceneNode->setRotation (vector3df(0,90,170));
//	sceneNode->addShadowVolumeSceneNode (0,-1,true,1000.0f);
//	sceneNode->setDebugDataVisible (1);

	smgr->addLightSceneNode (sceneNode,vector3df(0,0,0),video::SColorf(1.0f,1.0f,1.0f),200.0f,-1);

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновить действия Харвестера
void Harvester::Update()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновить действия Харвестера
/// \param Maps глобальная карта
void Harvester::Update(Map * Maps)
{
	ExtractingUnit::Update (Maps);
}
