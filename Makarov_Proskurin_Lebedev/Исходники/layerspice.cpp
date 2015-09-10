#include "LayerSpice.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
LayerSpice::LayerSpice()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param position	позиция модели объекта при создании
/// \param IDTEAM	идентификационный номер команды
LayerSpice::LayerSpice(irr::video::IVideoDriver    *   driver,
					   irr::scene::ISceneManager   *   smgr,
					   irr::core::vector3df position,
					   IDTeam IDTEAM)
{
	setInfo ("Спайсовое поле",100,1,IDTEAM);
	SpiceBox = new Spice(500,1000,IDTEAM);
	LayerSpice::setInfoModel (driver,smgr,"Textures/spice.jpg", position);

	isFree = true;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
LayerSpice::~LayerSpice()
{
	SpiceBox->~Spice ();
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция инициализации модели Спайсового поля
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param texture	текстура модели
/// \param position	позиция модели объекта при создании
void LayerSpice::setInfoModel (IVideoDriver *driver, ISceneManager *smgr, std::string texture, irr::core::vector3df position)
{
	tex = driver->getTexture(texture.c_str ());

	sceneNode = smgr->addCubeSceneNode ();
	sceneNode->setMaterialTexture(0, tex);
//	sceneNode->setMaterialFlag(video::EMF_LIGHTING, false);
	sceneNode->setMaterialFlag(video::EMF_LIGHTING, true);
	sceneNode->setScale (vector3df(5,0,5));
	sceneNode->setPosition (position);
	sceneNode->setDebugDataVisible (1);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновить спайсовое поле
void LayerSpice::Update()
{

}
