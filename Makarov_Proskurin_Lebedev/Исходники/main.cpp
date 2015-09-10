/// @author MaKaRoN_IV <br>	Макаров Александр

#include <stdafx.h>
#include "all_h.h"

using namespace irr;
using namespace core;
using namespace scene;
using namespace video;
using namespace io;
using namespace gui;

#ifdef _MSC_VER
#pragma comment(lib, "Irrlicht.lib")
#endif

irr::IrrlichtDevice         *   device  =   0;									///< Указатель на устройство
irr::scene::ISceneManager   *   smgr    =   0;									///< Указатель на менеджер сцены
irr::video::IVideoDriver    *   driver  =   0;									///< Указатель на драйвер устройства
irr::gui::IGUIEnvironment	*	guienv	=	0;									///< Указатель на графический интерфейс

int lastFPS = -1;                                                               ///< Значение последного FPS

////////////////////////////////////////////////////////////////////////////////
/// \brief Сделать скриншот
/// \param device устройство
void takeScreenshot(irr::IrrlichtDevice* device)
{
   irr::video::IVideoDriver* const driver = device->getVideoDriver();

   //get image from the last rendered frame
   irr::video::IImage* const image = driver->createScreenShot();
   if (image) //should always be true, but you never know. ;)
   {
	  //construct a filename, consisting of local time and file extension
	  irr::c8 filename[64];
	  snprintf(filename, 64, "Screenshots/screenshot_%u.png", device->getTimer()->getRealTime());

	  //write screenshot to file
	  if (!driver->writeImageToFile(image, filename))
		 device->getLogger()->log(L"Failed to take screenshot.", irr::ELL_WARNING);

	  //Don't forget to drop image since we don't need it anymore.
	  image->drop();
   }
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обработчик событий
class MyReceiver : public IEventReceiver
{
public:
	MyReceiver(){};
	~MyReceiver(){};
	bool OnEvent(const SEvent& event)
	{
		if(event.EventType == EET_KEY_INPUT_EVENT && event.KeyInput.PressedDown)
		{
			switch (event.KeyInput.Key)
			{
				case KEY_ESCAPE:
					device->closeDevice();
					break;
				case KEY_F12:
					takeScreenshot (device);
					break;
				default:
					break;
			}
		}
		return false;
	}
private:
};


////////////////////////////////////////////////////////////////////////////////
/// \brief Главный цикл
/// \return 0, если завершается без ошибок
int main()
{
	////////////////////////////////////////////////////////////////////////////
	// create a NULL device to detect screen resolution
	IrrlichtDevice *nulldevice = createDevice(video::EDT_NULL);

	core::dimension2d<u32> deskres = nulldevice->getVideoModeList()->getDesktopResolution();

	nulldevice -> drop();

	// now the dimensions can be used to create the real device
	//device = createDevice(video::EDT_DIRECT3D9, deskres, 32, true, false, true, &receiver);

	////////////////////////////////////////////////////////////////////////////
	MyReceiver myRec;
	device = createDevice( video::EDT_OPENGL, deskres, 32, true, true, true, &myRec);
	if (!device) return 1;                                                  // Не удалось создать по выбранному драйверу

	driver  = device->getVideoDriver();                                         // Получаем доступ к видеодрайверу для работы с прорисовкой геометрии																			///Здесь передаем указатель на драйвер для всех объектов с моделями или каждый раз передавать в спец функции
	smgr = device->getSceneManager();											// Получаем доступ к менеджеру сцены
	guienv = device->getGUIEnvironment();


//	ITexture* tex;
//	  tex = driver->getTexture("Textures/metal_8.jpg");

//	 smgr->addSkyBoxSceneNode (0,tex,0,0,0,0,0,-1);
	////////////////////////////////////////////////////////////////////////////
	irrWeatherManager* atmo = new irrWeatherManager(smgr);

	////////////////////////////////////////////////////////////////////////////
	scene::ITriangleSelector* selector = 0;                                     // Создаем интерфейс для работы с треугольниками

	//add RTS Camera
	RTSCamera* camera = new RTSCamera(device,smgr->getRootSceneNode(),smgr,-1,100.0f,300.0f,10.0f);
	camera->setTarget (vector3df(1600,0,1600));
	camera->setPosition(vector3df(1800,1000,1800));
	camera->setTranslateSpeed(100);												//speed of cam movement
	camera->setRotationSpeed(500);												//speed of cam rotation


	////////////////////////////////////////////////////////////////////////////
	Game * GameDune = new Game(driver,smgr);
//	ITexture* tex;
//	  tex = driver->getTexture("Textures/logo.jpg");

//	  IGUIImage* img;

//	  // Give to addImage() the XY coords of 2 points :
//	  img = guienv->addImage(core::rect<s32>(0,deskres.Height*0.9, deskres.Width,deskres.Height*1.3));
//	  img->setImage(tex);
//	  img->setScaleImage(true);
//	  driver->removeTexture(tex);
	ITexture* tex;
	tex = driver->getTexture("Textures/atreides.png");

	IGUIImage* img;

	// Give to addImage() the XY coords of 2 points :
	img = guienv->addImage(core::rect<s32>(deskres.Width*0.95,deskres.Height*0.1, deskres.Width*0.99, deskres.Height*0.2));
	img->setImage(tex);
	img->setScaleImage(true);
	driver->removeTexture(tex);

	tex = driver->getTexture("Textures/Ordos.png");

	// Give to addImage() the XY coords of 2 points :
	img = guienv->addImage(core::rect<s32>(deskres.Width*0.95,deskres.Height*0.25, deskres.Width*0.99, deskres.Height*0.35));
	img->setImage(tex);
	img->setScaleImage(true);
	driver->removeTexture(tex);


	tex = driver->getTexture("Textures/Harkonnen.png");

	// Give to addImage() the XY coords of 2 points :
	img = guienv->addImage(core::rect<s32>(deskres.Width*0.95,deskres.Height*0.4, deskres.Width*0.99, deskres.Height*0.5));
	img->setImage(tex);
	img->setScaleImage(true);
	driver->removeTexture(tex);

	////////////////////////////////////////////////////////////////////////////
	while(device->run())                                                        // Запускаем
	if (device->isWindowActive())
	{
		driver->beginScene(true, true, SColor(0));                              // Начинаем прорисовку сцены

		GameDune->Update (driver, smgr, guienv, deskres);
		smgr->drawAll();														// Прорисовываем все
		guienv->drawAll();
		driver->endScene();                                                     // Заканчиваем прорисовку сцены

		int fps = driver->getFPS();                                             // Получаем кол-во FPS
		if (lastFPS != fps)                                                     // Показываем FPS
		{
			core::stringw str = L"Dune III [";
			str += driver->getName();
			str += "] FPS:";
			str += fps;

			device->setWindowCaption(str.c_str());                              // Задаем заголовок окна

			lastFPS = fps;
		}
	}

	device->drop();

	////////////////////////////////////////////////////////////////////////////
	return 0;
}


