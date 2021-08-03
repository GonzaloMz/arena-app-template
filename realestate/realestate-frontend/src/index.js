import React from 'react';
import { render } from 'react-dom';
import { ArenaApp, arenaReducer, ArenaMapper, arenaMiddlewares } from 'frontend';
import * as serviceWorker from './serviceWorker';
import ReactDOM from 'react-dom';
//redux
import { Provider } from 'react-redux';
import { createStore , applyMiddleware, compose} from 'redux';
import { parentComponentsMap, componentsMap, componentsTypeMap, templatesMap } from './ComponentMapper.js';
import { screenConfigurationMap } from './ScreenMapper.js';
import { shapeConfigurationMap } from './ShapeMapper.js';
import { textMap } from './TextMapper.js';
import { filtersMap } from './FiltersMap.js';
import 'bootstrap/dist/js/bootstrap.min.js';
import './custom.scss';

import { BrowserRouter as Router, Redirect, Switch, Route} from 'react-router-dom'
import Menu from './Menu';
		
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const store = createStore(arenaReducer,{context:{}, controller:{}}, composeEnhancers(applyMiddleware(...arenaMiddlewares)))


const componentMapper = new ArenaMapper(
	parentComponentsMap,
	templatesMap,
	componentsMap,
	screenConfigurationMap,
	shapeConfigurationMap,
	filtersMap,
	componentsTypeMap,
	textMap.es,
	// "http://localhost:1312/"
	"http://192.168.0.14:1312/"
	// "http://api.costoya.com.ar/"
);



console.log(componentMapper);
ReactDOM.render(
	<Provider store={store}>

		<Router>
			<Menu options={[
				{
					label: "Nueva Tasación",
					href: "/create-appointment"
				},{
					label: "Tasaciones Pendientes",
					href: "/pending-appointments"
				},{
				// 	label: "Tasaciones en Progreso",
				// 	href: "/build/listContainer/view/appointment"
				// },{
					label: "Tasaciones Realizadas",
					href: "/assessments"
				},{
					label: "Propiedades Disponibles",
					href: "/estates"
				}
			]}></Menu>
			<Switch>
				<Redirect from="/create-appointment" push={true} to="/build/container/create/appointment" />
				<Redirect from="/view-users" push={true} to="/build/listcontainer/edit/user" />
				<Redirect from="/pending-appointments" push={true} to="/build/listContainer/view/appointment?status=ACTIVE,:,WITHOUT_DATE" />
				<Redirect from="/assessments" push={true} to="/build/listContainer/view/assessment?sugestedValue=0,;,10000000&appointment=2" />
				<Redirect from="/estates" push={true} to="/build/listContainer/view/estate" />
				<Redirect from="/estate" push={true} to="/build/container/view/estate/" />
				<Route component={()=>(<ArenaApp componentMapper={componentMapper} store={store} location={window.location}/>)}></Route>
			</Switch>
		</Router>
		
	</Provider>,
  document.getElementById('root')
);


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
