#pragma once

#ifndef WINDTURBINE_H
#define WINDTURBINE_H

#include "structure.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Ветряк - здание, производящее электроэнергию.
class WindTurbine : public Structure
{
	public:
		WindTurbine();
		virtual ~WindTurbine();
};

#endif // WINDTURBINE_H
////////////////////////////////////////////////////////////////////////////////
