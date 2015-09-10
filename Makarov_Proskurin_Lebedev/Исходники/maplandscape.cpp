#include "maplandscape.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
MapLandscape::MapLandscape()
{
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
MapLandscape::MapLandscape(IVideoDriver *driver, ISceneManager *smgr)
{
//	for(int i=0;i<64;i++)
//		for(int j=0;j<64;j++)
//		{
//			LayerLandscape * LLand = new LayerLandscape(driver, smgr, vector3df(50*i,0,50*j), OTHERS);
//			addLayerLandscape (LLand);
//		}

	LayerLandscape * LLand = new LayerLandscape(driver, smgr, vector3df(0,-51,0), OTHERS);
	addLayerLandscape (LLand);
//	irr::video::SColor cubeColour(255,255,255,255);
//	irr::scene::SMesh *dmes = new irr::scene::SMesh;
//	irr::scene::SMeshBuffer *buffer = new irr::scene::SMeshBuffer;
//	//вершины
//	int v_cnt = w * h * 4; //число вершин в полигонах
//	int p_cnt = 0; //счетчик полигонов, фактически номер первой вершины полигона из buffer->Vertices
//	buffer->Vertices.set_used(v_cnt);
//	for(int z=0; z<h; z++)
//	for(int x=0; x<w; x++)
//	{
//	buffer->Vertices[p_cnt]  = irr::video::S3DVertex(    -x,0,  z,   0,1,0, cubeColour, 0, 0);
//	buffer->Vertices[p_cnt+1]  = irr::video::S3DVertex(-x-1,0,  z,   0,1,0, cubeColour, 0, 1);
//	buffer->Vertices[p_cnt+2]  = irr::video::S3DVertex(-x-1,0,z+1,   0,1,0, cubeColour, 1, 1);
//	buffer->Vertices[p_cnt+3]  = irr::video::S3DVertex(  -x,0,z+1,   0,1,0, cubeColour, 1, 0);
//	p_cnt += 4;
//	}
//	//порядок обхода
//	int i_cnt = w * h * 6; //число индексов
//	p_cnt = 0; //счетчик полигонов, фактически номер первой вершины полигона из buffer->Vertices
//	buffer->Indices.set_used(i_cnt);
//	for(int i=0; i<i_cnt; i+=6) //за один шаг цикла описываем 2 треугольника из которых состоит полигон
//	{
//	buffer->Indices[i] = p_cnt;
//	buffer->Indices[i+1] = p_cnt+1;
//	buffer->Indices[i+2] = p_cnt+2;
//	buffer->Indices[i+3] = p_cnt;
//	buffer->Indices[i+4] = p_cnt+2;
//	buffer->Indices[i+5] = p_cnt+3;
//	p_cnt += 4;
//	}
//	buffer->recalculateBoundingBox();
//	dmes->addMeshBuffer(buffer);
//	buffer->drop();
//	dmes->setHardwareMappingHint(irr::scene::EHM_STATIC); //типа VBO
//	irr::scene::IMeshSceneNode *meshNode = smgr->addMeshSceneNode(dmes);
//	dmes->drop();
//	meshNode->setMaterialFlag(irr::video::EMF_LIGHTING, false);
//	meshNode->setMaterialTexture(0, tex);
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
MapLandscape::~MapLandscape()
{
	MapLayerLandscape.clear ();
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Добавить слой ландшафтного поля
/// \param LLand	ландшафтное поле
void MapLandscape::addLayerLandscape(LayerLandscape *LLand)
{
	MapLayerLandscape.push_back (LLand);
}
