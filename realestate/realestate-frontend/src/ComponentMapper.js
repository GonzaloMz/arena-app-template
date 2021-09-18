import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faWhatsapp } from '@fortawesome/free-brands-svg-icons'
import { faEye } from '@fortawesome/free-solid-svg-icons'
import React from 'react';
import { DateDisplay, PlaceSelector } from './utils';
import {ArenaContainerMode} from 'frontend';
export const componentsTypeMap = {

}
export const parentComponentsMap = {
    estate: {
        placeDescription: {
            10: {
                render:{
                    beforeRender: ({ entity={}, update, t }) => {
                        return <div className="custom-place-description">
                            <span className='place-description-item'>{t('publicSearch.placeDescription.environments')} {entity.environments || "--"}</span>
                            <span className='place-description-item'>{t('publicSearch.placeDescription.toilets')} {entity.toilets || "--"}</span>
                            <span className='place-description-item'>{t('publicSearch.placeDescription.carPlaces')} {entity.carPlaces || "--"}</span>
                            {/* <span className='place-description-item'>{t('publicSearch.placeDescription.squareMeterTotal')} {t(`app.backend.model.enums.SquareMeterRange.${entity.squareMeterTotal}`)}</span> */}
                        </div>
                    }
                }
            }
        },

    },
    estateSearch:{
        estate: {
            2:{
                render:{
                    beforeRender: ({entity})=> (<div className="operation-indicator d-none">
                        <div style={{width:'20%'}} className={`${entity.operation === 'SALE' ? 'bg-dark': 'bg-primary'}`}></div>
                    </div>)
                }
            }
        }
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
                               <button className='arena-full-button' onClick={()=>{history.push('/build/container/create/assessment?shapeName=assessmentCreation&appointment='+entity.id)}}>{t('createAssessment', 'Tasar ahora')}</button>
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
                               <button className='arena-full-button' onClick={()=>{history.push('/build/container/create/estate?shapeName=estateCreation&assessment='+entity.id)}}>{t('createEstate', 'Alta de propiedad')}</button>
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
                    console.log(entity.sugestedValue)
                    const url=encodeURI(`${window.location.protocol}//${window.location.hostname}/build/container/view/estate/${entity.id}`);
                    return (<div >
                        { mode === 'VIEW' && 
                            <div className="text-right">
                                <a className='detail-button p-2' href={`/build/container/view/estate/${entity.id}`}><FontAwesomeIcon icon={faEye} /> {t('share', 'Detalle')}</a>
                                <a className='share-button p-2' href={`https://api.whatsapp.com/send?text=${url}`}><FontAwesomeIcon icon={faWhatsapp} /> {t('share', 'Compartir')}</a>
                            </div>
                        }

                        { mode === 'EDIT' || mode === 'CREATE' && 
                            <div className="estate-after-render d-inline">
                                <DateDisplay value={entity.assessmentTimestamp} className="d-inline"></DateDisplay>
                                <span> {t('assessment.sugestedValue.label')} </span>
                                <span> - USD {entity.sugestedValue}</span>
                            </div>
                        }
                        
                    </div>)

                }
            }
        }
    },
    place:{
        default:{
            render:{
                beforeRender:({entity, mode, updateEntity, error, t, history})=>{
                    if(mode===ArenaContainerMode.VIEW)
                        return null;
                    
                    const update=(selectedGooglePlace)=>{
                        if(!selectedGooglePlace)
                            return;
                        const locality=selectedGooglePlace.address_components.find(c=>c.types.includes('locality'));
                        const route=selectedGooglePlace.address_components.find(c=>c.types.includes('route'));
                        const streetNumber=selectedGooglePlace.address_components.find(c=>c.types.includes('street_number'));
                        if(!locality || !route || !streetNumber){
                            updateEntity({...entity})
                            return;
                        }
                        const formattedAddress = `${route.long_name} ${streetNumber.long_name}`
                        updateEntity({
                            ...entity,
                            formattedAddress,
                            locality: locality.long_name
                        })
                    }
                    return <PlaceSelector
                        t={t}
                        update={update}
                    ></PlaceSelector>
                        
                }
            }
        }
    }
}
export const templatesMap = {
}
