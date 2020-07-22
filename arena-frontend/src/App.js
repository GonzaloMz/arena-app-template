import React from 'react';
import logo from './logo.svg';
import './App.css';
import ArenaContainer from './arena/ArenaContainer.js';
import ArenaListContainer from './arena/ArenaListContainer';
import { useParams } from "react-router";
import { CONTAINER_MODE } from './arena/enums';
import {
	BrowserRouter as Router,
	Switch,
	Route
} from "react-router-dom";
import queryString from 'query-string';
import { connect } from 'react-redux';
import { mapStateToProps, mapContextToProps } from './arena/constants';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEllipsisV } from '@fortawesome/free-solid-svg-icons';


const RouteContainer = (props) => {
	const { id, mode, controller } = props.match.params;
	const search = props.location.search;
	const values = queryString.parse(search);
	let shapeName = values.shapeName;
	if (!shapeName)
		shapeName = controller;
	const shapeConfiguration = props.componentMapper.getShapeConfiguration(shapeName);
	return (
		<ArenaContainer
			controller={controller}
			mode={CONTAINER_MODE[mode]} id={id} level={1}
			className="card p-5"
			shapeConfiguration={shapeConfiguration}
			componentMapper={props.componentMapper}
		/>
	);
}

const RouteListContainer = (props) => {
	const { id, mode, controller } = props.match.params;
	const search = props.location.search;
	const values = queryString.parse(search);
	let filterName = values.filterName;
	if (!filterName)
		filterName = controller;
	let filter = props.componentMapper.filtersMap[filterName];
	if (typeof filter === "function")
		filter = filter(props, values);
	let level = values.level;
	if (!level)
		level = 1;

	let shapeName = values.shapeName;
	if (!shapeName)
		shapeName = controller;
	const shapeConfiguration = props.componentMapper.getShapeConfiguration(shapeName);
	return (
		<ArenaListContainer
			controller={controller}
			mode={CONTAINER_MODE[mode]} level={level}
			className="card p-5"
			example={filter}
			template={{ entity: {} }}
			shapeConfiguration={shapeConfiguration?shapeConfiguration:{}}
			componentMapper={props.componentMapper}
		/>
	);
}

const ConectedRoutListContainer = connect(mapStateToProps)(RouteListContainer);

function App(props) {
	const search = props.location.search;
	const values = queryString.parse(search);
	let screenName = values.screenName;
	const screenConfiguration = props.componentMapper.getScreenConfiguration(screenName);
	return (
		<div className="App">
			{
				screenConfiguration.topDecorator &&
				<img src={screenConfiguration.topDecorator} className="w-100" alt="topDecorator" />
			}
			<div
				className="navbar navbar-expand navbar-dark bd-navbar px-0">
				<div className="container">

					<div className="row w-100 m-auto">
						<div className="col-2">
							{screenConfiguration.leftAction &&
								<a href="/">
									<img className="w-100" src={logo} alt="logo" />
								</a>
							}
						</div>
						<div className="col-8 m-auto text-center">
							<div >
								{
									screenConfiguration.title &&
									screenConfiguration.title(values, props.context)
								}
							</div>
						</div>
						<div className="col-2">
							{screenConfiguration.rigthAction &&
								<a href="/">
									<img className="w-100" src={logo} alt="logo" />
								</a>
							}
						</div>
						<div className="top-actions-bar">
							{ screenConfiguration.actions &&
								<div className="dropdown">
									<button className="btn bmd-btn-icon btn-primary dropdown-toggle active" type="button" id="ex1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										<FontAwesomeIcon color="#d7d5e9" icon={faEllipsisV} ></FontAwesomeIcon>
									</button>
									<div className="dropdown-menu dropdown-menu-left" aria-labelledby="ex1">
										{
											screenConfiguration.actions(values, props).map(
												(item) => (
													<button className="dropdown-item" type="button" {...item.props}>{item.label}</button>
												)
											)
										}
									</div>
								</div>
							}
						</div>
					</div>

				</div>
			</div>
			<div className="container">
				<Router>
					<Switch>
						<Route 
							path="/build/container/:mode/:controller/:id" 
							render={(containerProps)=>(<RouteContainer {...containerProps} componentMapper={props.componentMapper}/>)}>
						</Route>
						<Route
							path="/build/listContainer/:mode/:controller"
							render={(containerProps)=>(<ConectedRoutListContainer {...containerProps} componentMapper={props.componentMapper}/>)}>
						</Route>
					</Switch>
				</Router>
			</div>
		</div>
	);
}

export default connect(mapContextToProps)(App);
