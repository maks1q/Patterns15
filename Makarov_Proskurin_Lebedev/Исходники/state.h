#ifndef STATE_H
#define STATE_H

////////////////////////////////////////////////////////////////////////////////
/// \brief Состояние объекта
class State
{
	public:
		/// Перечисление состояния объекта
		enum IState
		{
			IIDLE,
			IEXPECT,
			IMOVE,
			ISEARCHRES,
			IGETRES,
			IGIVERES,
			ICOMEBACK,
			IBACK,
			ICANNOTFINDSPICE,
			IGOTOGETSPICE,
			IATTACK,
			IGOTOATTACK,
			ISEARCHENEMY,
			ICANNOTENEMYUNIT,
			IAMRECHARGED,
			IEXPLODE
		} MyState;

	public:
		/// @name Конструкторы
		/// @{
		State();
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~State();
		/// @}

		/// @name Sets
		/// @{
		void setState(IState NewState);
		/// @}

		/// @name Gets
		/// @{
		IState getState();
		/// @}
};

#endif // STATE_H
////////////////////////////////////////////////////////////////////////////////
