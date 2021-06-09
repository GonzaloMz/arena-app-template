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
import './custom.scss';
import 'bootstrap/dist/js/bootstrap.min.js';

import { BrowserRouter as Router, Redirect, Switch, Route} from 'react-router-dom'
		
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
	"http://localhost:1312/"
);

console.log(componentMapper);
ReactDOM.render(
	<Provider store={store}>
		<Router>
			<Switch>
				<Redirect from="/view-users" push={true} to="/build/listcontainer/edit/user" />
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
