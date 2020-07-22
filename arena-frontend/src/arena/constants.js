

export const mapStateToProps = (state, ownProps) => {
	return {
		loadingOwnShape: state.shapes.loadingShape[ownProps.controller],
		shape: state.shapes[ownProps.controller],
		context: state.context
	}
}

export const mapContextToProps = (state, ownProps) => {
	return {
		context: state.context
	}
}
