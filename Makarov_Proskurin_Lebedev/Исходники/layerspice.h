#pragma once

#ifndef LayerSpice_H
#define LayerSpice_H

#include "objectwithmodel.h"
#include "spice.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Слой спайсового поля.
class LayerSpice : public ObjectWithModel
{
	public:
		/// Указатель на склад спайса, содержащегося в спайсовом слое
		Spice * SpiceBox = NULL;
		bool isFree;
	public:
		/// @name Конструкторы
		/// @{
		LayerSpice();
		LayerSpice(irr::video::IVideoDriver *driver,
				   irr::scene::ISceneManager *smgr,
				   irr::core::vector3df position, IDTeam IDTEAM);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~LayerSpice();
		/// @}

		/// @name Функции
		/// @{
		virtual void setInfoModel(IVideoDriver *driver, ISceneManager *smgr, std::string texture, vector3df position);
		virtual void Update();
		/// @}
};

#endif // LayerSpice_H
////////////////////////////////////////////////////////////////////////////////
