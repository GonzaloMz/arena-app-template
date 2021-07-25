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
                        return (<div >
                            { mode !== 'CREATE' && 
                               <button className='arena-button' onClick={()=>{history.push('/build/container/create/assessment?shapeName=assessmentCreation&appointment='+entity.id)}}>{t('createAssessment', 'Tasar ahora')}</button>
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
            render: {
                afterRender: ({entity,t, mode, history})=>{
                    // if(!entity.appointmentDate){
                        return (<div >
                            { mode !== 'CREATE' && 
                               <button className='arena-button' onClick={()=>{history.push('/build/container/create/estate?assessment='+entity.id)}}>{t('createEstate', 'Alta de propiedad')}</button>
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
    }
}
export const templatesMap = {
}
