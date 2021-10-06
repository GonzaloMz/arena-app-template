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
import { ConfirmationScreen } from './screens/ConfirmationScreen';
import InputBuilder from './InputBuilder';
		
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
	// "https://api.costoya.com.ar/"
);

componentMapper.setInputBuilder(InputBuilder);


window.onload = function() {
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
						<Redirect from="/assessments" push={true} to="/build/listContainer/view/assessment?estateCreated=false" />
						<Redirect from="/estates" push={true} to="/build/container/search/estateSearch" />
						<Redirect from="/estate" push={true} to="/build/container/view/estate/" />
						<Redirect from="/" exact={true} to="build/container/search/estateSearch?shapeName=publicSearch" />
						<Redirect from="/appointment-creation-finish" push={true} to="/confirmation/appointment" />
						<Route path="/confirmation/appointment" strict={true} render={
							()=>(<ConfirmationScreen 
								name='appointment'
								nextUrl='/pending-appointments'
								backUrl='/create-appointment'
								componentMapper={componentMapper}>
							</ConfirmationScreen>)}/>
						<Route path="/confirmation/assessment" strict={true} render={
							()=>(<ConfirmationScreen 
								name='assessment'
								nextUrl='/assessments'
								backUrl='/pending-appointments'
								componentMapper={componentMapper}>
							</ConfirmationScreen>)}/>
						<Route path="/confirmation/estate" strict={true} render={
							()=>(<ConfirmationScreen 
								name='estate'
								nextUrl='/estates'
								backUrl='/assessments'
								componentMapper={componentMapper}>
							</ConfirmationScreen>)}/>
						<Route path="/build/:description" component={()=>(<ArenaApp componentMapper={componentMapper} store={store} location={window.location}/>)}></Route>
					</Switch>
				</Router>
		
		</Provider>,
	document.getElementById('root')
	);
}

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
