import React from 'react';
import { render } from 'react-dom';
import { ArenaApp, arenaReducer, ArenaMapper } from '@gonzalomz/arena-frontend';
import * as serviceWorker from './serviceWorker';
import ReactDOM from 'react-dom';
//redux
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import { parentComponentsMap, componentsMap, componentsTypeMap, templatesMap } from './ComponentMapper.js';
import { screenConfigurationMap } from './ScreenMapper.js';
import { shapeConfigurationMap } from './ShapeMapper.js';
import { textMap } from './TextMapper.js';
import { filtersMap } from './FiltersMap.js';
import './custom.scss';
import 'bootstrap/dist/js/bootstrap.min.js';
		

const store = createStore(arenaReducer,
	window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__())

const componentMapper = new ArenaMapper(
	parentComponentsMap,
	templatesMap,
	componentsMap,
	screenConfigurationMap,
	shapeConfigurationMap,
	filtersMap,
	componentsTypeMap,
	textMap.es
);

ReactDOM.render(
	<Provider store={store}>
		<ArenaApp componentMapper={componentMapper} store={store} location={window.location}/>
	</Provider>,
  document.getElementById('root')
);


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
