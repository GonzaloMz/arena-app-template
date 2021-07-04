import React from 'react';
export const componentsTypeMap = {

}
export const parentComponentsMap = {
    appointment: {
        userId: {
            1: {
                mode : ()=> 'HIDE'
            }
        },

    }
}
export const componentsMap = {
    appointment:{
        default : {
            render: {
                afterRender: ({entity,t, mode, history})=>{
                    // if(!entity.appointmentDate){
                        return (<div className='appointment-date'>
                            {!entity.appointmentDate && <samp className='text-warning'>{t('withoutDate', 'Sin cita')}</samp>}
                            { mode !== 'CREATE' && 
                               <button className='arena-button' onClick={()=>{history.push('/build/container/create/assessment?appointment='+entity.id)}}>{t('createAssessment', 'Tasar ahora')}</button>
                            }
                        </div>)
                    // }
                    // const date = new Date (entity.appointmentDate);

                    // return (
                    //     <div className='appointment-date'>
                    //         <div>{`${date.getDate()+1}/${date.getMonth()+1}`}</div>
                    //     </div>
                    // )
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
