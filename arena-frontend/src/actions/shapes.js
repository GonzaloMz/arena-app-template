export const shapeLoaded=(name,shape)=>{
	let action={
		type:'SHAPE_LOADED',
		shape:{}
	}
	action.shape[name]=shape;
	return action;
};

export const loadingShape=(name)=>{
	let action={
		type:'SHAPE_LOADING',
		loadingShape: {}
	}
	action.loadingShape[name] = true;
	return action;
};
