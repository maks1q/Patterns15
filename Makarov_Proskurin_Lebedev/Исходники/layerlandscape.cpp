#include "layerlandscape.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief LayerLandscape::LayerLandscape
/// Конструктор по умолчанию
LayerLandscape::LayerLandscape()
{
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param position	позиция модели объекта при создании
/// \param IDTEAM идентификационный номер команды
LayerLandscape::LayerLandscape(irr::video::IVideoDriver    *   driver,
							   irr::scene::ISceneManager   *   smgr,
							   irr::core::vector3df position,
							   IDTeam IDTEAM)
{
	setInfo ("Слой ландшафта", 100, 0, IDTEAM);
	LayerLandscape::setInfoModel (driver,smgr,"Textures/sand.jpg", position);

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
LayerLandscape::~LayerLandscape()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Инициализация модели Ландшафтного поля
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param texture	текстура модели
/// \param position	позиция модели объекта при создании
void LayerLandscape::setInfoModel(IVideoDriver *driver, ISceneManager *smgr, std::string texture, irr::core::vector3df position)
{
	tex = driver->getTexture(texture.c_str ());

	sceneNode = smgr->addCubeSceneNode ();
	sceneNode->setMaterialTexture(0, tex);

//	sceneNode->setMaterialFlag(video::EMF_LIGHTING, false);
	sceneNode->setMaterialFlag(video::EMF_LIGHTING, true);
	sceneNode->setScale (vector3df(640,10,640));
	sceneNode->setPosition (position);
//	sceneNode->setDebugDataVisible (1);
}
