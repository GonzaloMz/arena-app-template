import React from 'react';
import Select from 'react-select'

const colors={
    primary:'#eb833b',
    gray400:"#C9C7C7"
}

const inputBuilder = {
    buildSelect: (commonProps, options, placeholderString)=>{
        const formattedOptions = options.map(o=>({value:o.value, label:o.text}))
        const _onChange = (value) => {
            console.log(value);
            commonProps.onBlur({target:value});
        }

        return <Select value={formattedOptions.find(o=>o.value===commonProps.value) } onChange={_onChange}  classNamePrefix='arena' className=''
            menuShouldScrollIntoView={true}  
            isSearchable={false}  
            // corrección del problema de z-index --------- 
            menuPortalTarget={document.body} 
            styles={{ menuPortal: base => ({ ...base, zIndex: 9999 }) }}
            //---------------------------------------------
            theme={theme => {
                    console.log(theme);
                        return {
                            ...theme,
                            borderRadius: "2px",
                            colors: {
                                ...theme.colors,
                                primary25: colors.primary,
                                primary: colors.gray400,
                            },
                    }
                }}
            placeholder={placeholderString || ""} options={formattedOptions} />
    }
}

export default inputBuilder;