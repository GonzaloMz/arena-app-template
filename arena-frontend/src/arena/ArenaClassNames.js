
export const modalClassNames = (action) => (
	{
		closeButton: `arena-close-modal-button arena-close-${action}-modal-button`,
		modal: "arena-modal"
	}
);

export const getClassNameBasic = (prefix, props, sufix) => {
		let {
			name,
			controller 
		} = props;
		let baseClass = prefix+sufix;
		baseClass=baseClass.replace('--','-');
		return `${baseClass} ${prefix}name-${name}${sufix} ${prefix}controller-${controller}${sufix}`;
	}
