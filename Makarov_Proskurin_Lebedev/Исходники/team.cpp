#include "team.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор по умолчанию
Team::Team()
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Конструктор с параметрами
/// \param driver	драйвер устройства
/// \param smgr		менеджер сцены
/// \param IDTEAM	идентификационный номер команды
/// \param position позиция базы при создании
Team::Team(irr::video::IVideoDriver *driver,
		   irr::scene::ISceneManager *smgr,
		   irr::core::vector3df position,
		   IDTeam IDTEAM)
{
	setIDTeam (IDTEAM);
	SpiceTeam = new Spice(2000,10000,IDTEAM);
	EPowerTeam = new ElectricPower(0,100000,IDTEAM);

	/// Установка названия команды по идентификационному номеру
	switch (IDTEAM)
	{
		case ATREIDES:
		{
			setNameTeam ("Atreides");
			break;
		}
		case ORDOS:
		{
			setNameTeam ("Ordos");
			break;
		}
		case HARKONNEN:
		{
			setNameTeam ("Harkonnen");
			break;
		}
		case OTHERS:
		{
			setNameTeam ("Others");
		}
		default:
		{
			setNameTeam ("Empty");
			break;
		}
	}

	MyBase = new ConstructionPlant(driver, smgr, position, IDTEAM, SpiceTeam);

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Деструктор
Team::~Team()
{
	TeamCombat.clear ();
	TeamExtract.clear ();
	TeamStructure.clear ();
	MyBase = NULL;
	SpiceTeam->~Spice ();
	EPowerTeam->~ElectricPower ();
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Обновление действий всех юнитов и зданий команды
void Team::Update(IVideoDriver *driver,
				  ISceneManager *smgr,
				  Map * Maps,
				  std::vector<Unit*> Units,
				  std::vector<Ammunition *> *Ammo)
{
	if(!TeamExtract.size () || TeamExtract.size ()<3)
	{
		Harvester *Harv = MyBase->createHarv (driver,smgr);
		if(Harv)
			TeamExtract.push_back(Harv);
	}
	else
	{
		Tank *Tan = MyBase->createTank (driver,smgr);
		if(Tan)
			TeamCombat.push_back(Tan);
	}

	for(int i=0;i<TeamCombat.size (); i++)
	{
		if(TeamCombat[i]->MyState.getState() == State::IEXPLODE)
		{
			TeamCombat.erase (TeamCombat.begin ()+i);
		}
		else
		{
			TeamCombat[i]->Update(driver, smgr, Maps, Units, Ammo);
		}

	}

	for(int i=0;i<TeamExtract.size (); i++)
	{
		if(TeamExtract[i]->MyState.getState() == State::IEXPLODE)
		{
//			TeamExtract[i].
			TeamExtract.erase (TeamExtract.begin ()+i);
		}
		else
		{
			TeamExtract[i]->Update(Maps);
		}

		Maps->Update ();
	}
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Функция установки имени команды
/// \param NameTeam	 имя команды
void Team::setNameTeam(std::string NameTeam)
{
	this->NameTeam = NameTeam;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Получить название команды
/// \return Название команды
std::string Team::getNameTeam()
{
	return NameTeam;
}

////////////////////////////////////////////////////////////////////////////////
/// \brief Добавить юнита в команду
/// \param UnitTeam	юнит для добавления в команду
void Team::addUnit(Unit *UnitTeam)
{

}

////////////////////////////////////////////////////////////////////////////////
/// \brief Добавить здание в команду
/// \param StructureTeam здание, для добавления в команду
void Team::addStructure(Structure *StructureTeam)
{

}
