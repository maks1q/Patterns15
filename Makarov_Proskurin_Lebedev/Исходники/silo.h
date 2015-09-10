#pragma once

#ifndef SILO_H
#define SILO_H

#include "structure.h"
#include "spice.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Силос - здание, хранит в себе ресурс: спайс.
class Silo : public Structure
{
	public:
		/// Склад спайса в силосе
		Spice SiloSpice;
	public:
		/// @name Конструкторы
		/// @{
		Silo();
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Silo();
		/// @}

};

#endif // SILO_H
////////////////////////////////////////////////////////////////////////////////
