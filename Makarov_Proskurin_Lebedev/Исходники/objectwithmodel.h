#pragma once

#ifndef OBJECTWITHMODEL_H
#define OBJECTWITHMODEL_H

#include "object.h"

using namespace irr;
using namespace core;
using namespace scene;
using namespace video;
using namespace io;
using namespace gui;

////////////////////////////////////////////////////////////////////////////////
/// \brief Класс объекта, имеющего 3D-модель и ее текстуру.
class ObjectWithModel : public Object
{
	public:
		/// Указатель на текстуру объекта
		irr::video::ITexture        *   tex			=   NULL;
		/// Указатель на модель объекта
		irr::scene::IMeshSceneNode	*	sceneNode	=	NULL;
	public:
		/// @name Конструкторы
		/// @{
		ObjectWithModel();
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~ObjectWithModel();
		/// @}

		/// @name Sets
		/// @{
		virtual void setInfoModel(irr::video::IVideoDriver    *   driver,
								 irr::scene::ISceneManager   *   smgr,
								 std::string texture,
								 irr::core::vector3df position );
		/// @}

		/// @name Функции
		/// @{
		virtual void Update();
		/// @}
};

#endif // OBJECTWITHMODEL_H
////////////////////////////////////////////////////////////////////////////////
