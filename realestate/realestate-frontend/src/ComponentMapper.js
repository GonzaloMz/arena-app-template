import React from 'react';
export const componentsTypeMap = {

}
export const parentComponentsMap = {
    appointment: {
        userId: {
            1: {
                mode : ()=> 'HIDE',
                render:{
                    beforeRender: ({entity})=>(
                        <div className="user-level-1">
                            <div class="user-name">{`${entity.name} ${entity.lastName}`}</div>
                        </div>
                    ),
                    // afterRender: ({entity})=>(
                    //     <div className="user-level-1">
                    //         <span class="user-phone">{entity.phone}</span>
                    //     </div>
                    // )
                }
            }
        },
        placeId: {
            1: {
                mode : ()=> 'HIDE',
                render:{
                    beforeRender: ({entity})=>(
                        <div className="place-level-1">
                            <span className="place-address">{entity.formattedAddress} </span>
                        </div>
                    )
                }
            }
        },

    }
}
export const componentsMap = {
    appointment:{
        default : {
            render: {
                afterRender: ({entity,t})=>{
                    if(!entity.appointmentDate){
                        return (<div>
                            {t('withoutDate', 'Sin cita')}
                        </div>)
                    }
                    const date = new Date (entity.appointmentDate);

                    return (
                        <div className='appointment-date'>
                            {`${date.getDate()+1}/${date.getMonth()+1}`}
                        </div>
                    )
                }
            }
        }
    },
    assessment: {
        default:{
            createTemplate: (search) => {
                return {
                    appointment:search.appointment
                }
            }
        }
    }
}
export const templatesMap = {
}
