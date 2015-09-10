#include "game.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
Game::Game()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
Game::Game(IVideoDriver *driver, ISceneManager *smgr)
{
	Maps = new Map(driver,smgr);
	Units.clear ();

	srand (time(NULL));
//	Team * Teamgame1 = new Team(driver,smgr, vector3df(-1500 + (rand ()%1500), 25.5, -1500 + (rand()%1500)), ORDOS);
	Team * Teamgame1 = new Team(driver,smgr, vector3df(-2000, 25.5, -2000), ORDOS);
	addTeam (Teamgame1);

	srand (time(NULL));
//	Team * Teamgame2 = new Team(driver,smgr, vector3df(0 + (rand ()%1500), 25.5, -1500 + (rand()%1500)), ATREIDES);
	Team * Teamgame2 = new Team(driver,smgr, vector3df(2000, 25.5, -2000), ATREIDES);
	addTeam (Teamgame2);

	srand (time(NULL));
	Team * Teamgame3 = new Team(driver,smgr, vector3df(0, 25.5,2000), HARKONNEN);
	addTeam (Teamgame3);

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
Game::~Game()
{
	Units.clear ();
	Teams.clear ();
	Maps->~Map ();
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновить игру
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param guienv	менеджер интерфейса
/// \param deskres	размеры окна
void Game::Update(IVideoDriver *driver, ISceneManager *smgr, irr::gui::IGUIEnvironment	*guienv, core::dimension2d<u32> deskres)
{
	int numTeam = 3;
	for(int i=0; i < Teams.size ();i++)
	{
		Teams[i]->Update(driver, smgr, Maps, Units, &Ammo);

		Units.clear ();

		for( int i=0; i < Teams.size ();i++)
		{
			for( int j=0; j<Teams[i]->TeamCombat.size();j++)
			{
				if(Teams[i]->TeamCombat[j]->MyState.getState() != State::IEXPLODE)
				Units.push_back (Teams[i]->TeamCombat[j]);
			}
			for( int k=0; k<Teams[i]->TeamExtract.size();k++)
			{
				if(Teams[i]->TeamExtract[k]->MyState.getState() != State::IEXPLODE)
				Units.push_back (Teams[i]->TeamExtract[k]);
			}
		}

		if(Teams[i]->TeamCombat.size()==0 && Teams[i]->TeamExtract.size()==0 )
		{
			ITexture* tex;
			IGUIImage* img;
			switch(Teams[i]->getIDTeam())
			{
				case ATREIDES:
				{
					tex = driver->getTexture("Textures/atreides_death.png");

					// Give to addImage() the XY coords of 2 points :
					img = guienv->addImage(core::rect<s32>(deskres.Width*0.95,deskres.Height*0.1, deskres.Width*0.99, deskres.Height*0.2));
					img->setImage(tex);
					img->setScaleImage(true);
					driver->removeTexture(tex);

					numTeam--;
					Teams.erase (Teams.begin ()+i);
					break;
				}
				case ORDOS:
				{
					tex = driver->getTexture("Textures/Ordos_death.png");

					// Give to addImage() the XY coords of 2 points :
					img = guienv->addImage(core::rect<s32>(deskres.Width*0.95,deskres.Height*0.25, deskres.Width*0.99, deskres.Height*0.35));
					img->setImage(tex);
					img->setScaleImage(true);
					driver->removeTexture(tex);
					Teams.erase (Teams.begin ()+i);
					numTeam--;
					break;
				}
				case HARKONNEN:
				{
					tex = driver->getTexture("Textures/Harkonnen_death.png");

					// Give to addImage() the XY coords of 2 points :
					img = guienv->addImage(core::rect<s32>(deskres.Width*0.95,deskres.Height*0.4, deskres.Width*0.99, deskres.Height*0.5));
					img->setImage(tex);
					img->setScaleImage(true);
					driver->removeTexture(tex);
					Teams.erase (Teams.begin ()+i);
					numTeam--;
					break;
				}
				default:
				{
					break;
				}
			}
		}
	}

	bool OneTeam = true;
	IDTeam OTeam = Teams[0]->getIDTeam();
	for(int i=0; i < Teams.size ();i++)
	{
		if(OTeam!=Teams[i]->getIDTeam())
			OneTeam = false;
	}
	if(OneTeam)
	{
		ITexture* tex;
		IGUIImage* img;

		tex = driver->getTexture("Textures/victory.jpg");

		// Give to addImage() the XY coords of 2 points :
		img = guienv->addImage(core::rect<s32>(deskres.Width*0.3,deskres.Height*0.3, deskres.Width*0.7,deskres.Height*0.7));
		img->setImage(tex);
		img->setScaleImage(true);
		driver->removeTexture(tex);

		switch(Teams[0]->getIDTeam())
		{
			case ATREIDES	:
			{
				tex = driver->getTexture("Textures/Atreides.png");

				// Give to addImage() the XY coords of 2 points :
				img = guienv->addImage(core::rect<s32>(deskres.Width*0.45,deskres.Height*0.75, deskres.Width*0.55,deskres.Height*0.90));
				img->setImage(tex);
				img->setScaleImage(true);
				driver->removeTexture(tex);
				break;
			}
			case ORDOS		:
			{
				tex = driver->getTexture("Textures/Ordos.png");

				// Give to addImage() the XY coords of 2 points :
				img = guienv->addImage(core::rect<s32>(deskres.Width*0.45,deskres.Height*0.75, deskres.Width*0.55,deskres.Height*0.90));
				img->setImage(tex);
				img->setScaleImage(true);
				driver->removeTexture(tex);
				break;
			}
			case HARKONNEN	:
			{
				tex = driver->getTexture("Textures/Harkonnen.png");

				// Give to addImage() the XY coords of 2 points :
				img = guienv->addImage(core::rect<s32>(deskres.Width*0.45,deskres.Height*0.75, deskres.Width*0.55,deskres.Height*0.90));
				img->setImage(tex);
				img->setScaleImage(true);
				driver->removeTexture(tex);
				break;
			}
			default			:
			{
				break;
			}
		}
	}

	for(int i = 0; i < Ammo.size (); i++)
	{
		UpdateUnits ();
		if(Ammo[i]->MyState.getState() == State::IEXPLODE)
		{
			for(int j = 0; j<Units.size (); j++)
			{
				if(Units[j]->getIDTeam() != Ammo[i]->getIDTeam())
				if(Ammo[i]->sceneNode->getPosition ().
				   getDistanceFrom (Units[j]->sceneNode->getPosition ())
				   <= Ammo[i]->getRadiusOfDefeat())
				{
					Units[j]->setArmor(Units[j]->getArmor() - Ammo[i]->getDamage()*Units[j]->getRatioArmor());
				}
			}
			Ammo[i]->Update();
			Ammo.erase (Ammo.begin () + i);
		}
		else
		{
			Ammo[i]->Update();
		}
	}
}

void Game::UpdateUnits()
{
	for(int i=0; i < Teams.size ();i++)
	{
		Units.clear ();

		for( int i=0; i < Teams.size ();i++)
		{
			for( int j=0; j<Teams[i]->TeamCombat.size();j++)
			{
				if(Teams[i]->TeamCombat[j]->MyState.getState() != State::IEXPLODE)
				Units.push_back (Teams[i]->TeamCombat[j]);
			}
			for( int k=0; k<Teams[i]->TeamExtract.size();k++)
			{
				if(Teams[i]->TeamExtract[k]->MyState.getState() != State::IEXPLODE)
				Units.push_back (Teams[i]->TeamExtract[k]);
			}
		}
	}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Добавить команду в игру
/// \param TeamGame Команда для добавления в игру
void Game::addTeam(Team *TeamGame)
{
	this->Teams.push_back (TeamGame);
}
