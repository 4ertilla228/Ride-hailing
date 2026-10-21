# Ride-hailing

##### 1.Таблица ответственности класса

Class                      |	Responsibility				
PricingContext             |	Delegates fare computation to the currently assigned pricing algorithm				
StandardPricingStrategy    |	Calculates fare using fixed per-kilometer rates	
SurgePricingStrategy       |	Calculates fare multiplying base rate by external demand data				
AirportFlatRateStrategy    |	Returns fixed flat fare applicable for airport trips		
DriverMatchingContext      |	Delegates driver selection to the current matching algorithm				
NearestDriverStrategy      |	Selects the driver having minimum physical distance to client				
HighestRatedDriverStrategy |	Selects the driver holding maximum customer rating score				
LongestIdleDriverStrategy  |	Selects the driver experiencing maximum waiting time
StrategySelector           |	Determines optimal strategies evaluating current trip parameters automatically				
DemandSensor               | 	Provides external real-time multiplier data for specific zones


##### 2. Стратегия против  состояния против  шаблонного метода
   
Почему в этом решении используется стратегия:
В сфере такси требуются взаимозаменяемые алгоритмы для расчета стоимости поездки 
и подбора водителей. Шаблон «Стратегия» выбран вместо шаблона «Состояние», 
потому что контекст (PricingContext, DriverMatchingContext) не меняет свое поведение в 
зависимости от внутренних переходов между состояниями. Поездка не переходит автоматически 
из состояния «Стандарт» в состояние «Аэропорт». Клиент или селектор сам выбирает конкретный 
алгоритм, необходимый для выполнения всей задачи.

Шаблон «Стратегия» выбран вместо шаблонного метода, потому что эти алгоритмы полностью 
различаются по механике выполнения, а не имеют общую основу. Шаблонный метод предполагает 
наследование для переопределения отдельных шагов базового класса. В нашем решении используется 
композиция: контекст делегирует всю задачу внешнему объекту, полностью изолируя алгоритмическую 
логику от класса Context и соблюдая принцип открытости/закрытости.

##### 3. Ссылки на Git-рефакторинг