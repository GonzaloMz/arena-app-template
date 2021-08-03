import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faWhatsapp } from '@fortawesome/free-brands-svg-icons'
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
                               <button className='arena-button' onClick={()=>{history.push('/build/container/create/estate?shapeName=estateCreation&assessment='+entity.id)}}>{t('createEstate', 'Alta de propiedad')}</button>
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
    estate:{
        default:{
            render:{
                afterRender:({entity,t, mode, history})=>{
                    const url=encodeURI(`${window.location.protocol}//${window.location.hostname}/build/container/view/estate/${entity.id}`);
                    return (<div >
                        { mode === 'VIEW' && 
                            <div className="text-right">
                                <a className='share-button p-2' href={`https://api.whatsapp.com/send?text=${url}`}><FontAwesomeIcon icon={faWhatsapp} /> {t('share', 'Compartir')}</a>
                            </div>
                        }
                        
                    </div>)

                }
            }
        }
    }
}
export const templatesMap = {
}
