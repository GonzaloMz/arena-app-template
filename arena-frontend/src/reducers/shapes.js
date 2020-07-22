const shapes = (state = { loadingShape : {}}, action) => {
	switch (action.type) {
		case 'SHAPE_LOADED':
			return {
				...state,
				...action.shape
			}
		case 'SHAPE_LOADING':
			return {
				...state,
				loadingShape:{
					...action.loadingShape
				}
			}
		default:
			return state
	}
}

export default shapes
