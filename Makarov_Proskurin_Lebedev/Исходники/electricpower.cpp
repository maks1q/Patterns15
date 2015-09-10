#include "electricpower.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
ElectricPower::ElectricPower()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param NumPower		текущее значение ресурса
/// \param MaxNumPower  максимальное значение ресурса
/// \param IDTEAM идентификационный номер команды
ElectricPower::ElectricPower(int NumPower, int MaxNumPower, IDTeam IDTEAM)
{
	setInfo ("Электроэнергия",50,2, IDTEAM);
	setInfoRes (NumPower,MaxNumPower);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
ElectricPower::~ElectricPower ()
{

}
