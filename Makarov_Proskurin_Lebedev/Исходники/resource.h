#pragma once

#ifndef RESOURCE_H
#define RESOURCE_H

#include "object.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Общий класс для ресурсов: Спайс, Электроэнергия и т.д.
class Resource : public Object
{
	private:
		/// Текущее количество ресурса
		int Res;
		/// Максимальное значение ресурса
		int MaxRes;
	public:
		/// @name Конструкторы
		/// @{
		Resource();
		Resource(int Res, int MaxRes);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Resource();
		/// @}

		/// @name Sets
		/// @{
		void setRes(int Res);
		void setMaxRes(int MaxRes);
		void setInfoRes(int Res, int MaxRes);
		/// @}

		/// @name Gets
		/// @{
		int getRes();
		int getMaxRes();
		/// @}

		/// @name Функции
		/// @{
		int plusRes(int Res);
		int minusRes(int Res);
		int limitForAdd();
		virtual void toShowInfo();
		/// @}

		/// @name Проверки
		/// @{
		bool emptyRes();
		bool isFull();
		/// @}
};

#endif // RESOURCE_H
////////////////////////////////////////////////////////////////////////////////
