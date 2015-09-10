#include "Map.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
Map::Map()
{
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
Map::Map(IVideoDriver *driver, ISceneManager *smgr)
{
	IMapLandscape	= new MapLandscape(driver,smgr);
	IMapSpice		= new MapSpice(driver,smgr);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
Map::~Map()
{
	IMapSpice->~MapSpice ();
	IMapLandscape->~MapLandscape ();
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Создать карту
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
void Map::createMap(IVideoDriver *driver, ISceneManager *smgr)
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновить элементы карты
void Map::Update()
{
	IMapSpice->Update ();
	IMapLandscape->Update ();
}

