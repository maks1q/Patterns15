#pragma once

#ifndef OBJECT_H
#define OBJECT_H

#include "stdafx.h"
#include "idteam.h"
#include "state.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Общий класс для всех классов.
class Object
{
	public:
		/// Название объекта
		std::string Name;
		/// Идентификационный номер объекта
		int ID;
		/// Идентификационный номер группы объекта
		int IDGroup;
		/// Идентификационный номер объекта в группе
		int IDInGroup;
		/// Идентификационный номер команды
		IDTeam IDTEAM;
		/// Переменная, хранящая состояние объекта
		State MyState;
	public:
		/// @name Конструкторы
		/// @{
		Object();
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Object();
		/// @}

		/// @name Sets
		/// @{
		void setName(std::string Name);
		void setID(int ID);
		void setIDGroup(int IDGroup);
		void setIDInGroup(int IDInGroup);
		void setIDTeam(IDTeam IDTEAM);
		void setInfo(std::string Name, int IDGroup, int IDInGroup, IDTeam IDTEAM);
		/// @}

		/// @name Gets
		/// @{
		std::string getName();
		int getID();
		int getIDGroup();
		int getIDInGroup();
		IDTeam getIDTeam();
		/// @}

		/// @name Функции
		/// @{
		virtual void toShowInfo();
		virtual void Update();
		/// @}
};

#endif // OBJECT_H
////////////////////////////////////////////////////////////////////////////////
