#include "mapspice.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
MapSpice::MapSpice()
{
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
MapSpice::MapSpice(IVideoDriver *driver, ISceneManager *smgr)
{
	for(int i=0;i<20;i++)
		for(int j=0;j<20;j++)
		{
			LayerSpice * LSpice = new LayerSpice(driver, smgr, vector3df(100*(rand ()%64)-3136,0,100*(rand ()%64)-3136),OTHERS);
			addLayerSpice (LSpice);
		}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
MapSpice::~MapSpice()
{
	MapLayerSpice.clear ();
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция добавления слоя спайсового поля
/// \param LSpice	слой спайсового поля
///
void MapSpice::addLayerSpice(LayerSpice *LSpice)
{
	MapLayerSpice.push_back (LSpice);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновление карты спайсовых полей
void MapSpice::Update()
{
	for(int i=0; i<MapLayerSpice.size ();i++)
	{
		if(MapLayerSpice[i]->SpiceBox->emptyRes())
		{
			delete MapLayerSpice[i];
			MapLayerSpice[i] = NULL;
			MapLayerSpice.erase (MapLayerSpice.begin ()+i);

		}
	}

	//MapLayerSpice.swap (MapLayerSpice);
	for(int i=0; i<MapLayerSpice.size ();i++)
	{
		MapLayerSpice[i]->Update();
	}

}
