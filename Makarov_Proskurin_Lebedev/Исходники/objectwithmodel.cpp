#include "objectwithmodel.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief ObjectWithModel::ObjectWithModel
/// Конструктор по умолчанию
ObjectWithModel::ObjectWithModel()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
ObjectWithModel::~ObjectWithModel()
{
	sceneNode->remove ();
	sceneNode = NULL;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Установить данные о модели
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param texture	текстура модели
/// \param position позиция модели при создании
void ObjectWithModel::setInfoModel(irr::video::IVideoDriver    *   driver,
								  irr::scene::ISceneManager   *   smgr,
								  std::string texture,
								  irr::core::vector3df position )
{
	tex = driver->getTexture(texture.c_str ());

	sceneNode = smgr->addCubeSceneNode ();
	sceneNode->setMaterialTexture(0, tex);
	sceneNode->setMaterialFlag(video::EMF_LIGHTING, false);
	sceneNode->setScale (vector3df(5,2,5));
	sceneNode->setPosition ( position );

	sceneNode->setDebugDataVisible (1);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновление действий объекта с моделью
void ObjectWithModel::Update()
{

}
