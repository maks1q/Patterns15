#pragma once

#ifndef DIVISION_H
#define DIVISION_H

#include "object.h"
#include "unit.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Подразделение - содержит определенную группу, состоящую из юнитов.
class Division : public Object
{
	private:
		/// Контейнер юнитов - текущее подразделение
		std::vector<Unit *> ThisDivision;

		/// \todo добавить класс mission, класс содержащий в себе задачи и цели
	public:
		/// @name Конструкторы
		/// @{
		Division();
		Division(IDTeam IDTEAM);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual  ~Division();
		/// @}
};

#endif // DIVISION_H
////////////////////////////////////////////////////////////////////////////////
