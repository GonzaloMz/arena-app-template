import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faWhatsapp } from '@fortawesome/free-brands-svg-icons'
import { faEye } from '@fortawesome/free-solid-svg-icons'
import React from 'react';
import { DateDisplay, LocationMapRender, loggedIn, PlaceSelector, RentDatePicker, ViewPriceRender } from './utils';
import {ArenaContainerMode} from 'frontend';
import Carousel from 'react-elastic-carousel';
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
        }
    },
    // estateSearch:{
    //     estate: {
    //         2:{
    //             render:{
    //                 beforeRender: ({entity})=> (<div className="operation-indicator d-none">
    //                     <div style={{width:'20%'}} className={`${entity.operation === 'SALE' ? 'bg-dark': 'bg-primary'}`}></div>
    //                 </div>)
    //             }
    //         }
    //     }
    // }
}
export const componentsMap = {
    appointment:{
        default : {
            render: {
                // beforeRender: ({entity,t, mode, history})=>{
                //     return (<div >
                //         { mode === 'VIEW' && 
                //             <button className='arena-full-button' 
                //             onClick={ 
                //                 ()=>{
                //                     fetch()
                //                 }
                //             }>
                //                     {t('createAssessment', 'Tasar ahora')}
                //             </button>
                //         }
                        
                //     </div>)
                // },
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
                    console.log(entity)
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
                                <ViewPriceRender className='d-inline' entity={entity} value={entity.sugestedValue} t={t} mode={ArenaContainerMode.VIEW}></ViewPriceRender>
                            </div>
                        }
                    
                    </div>)

                }
            }
        },
        2:{
            render:{
                afterRender:({entity,t, mode, history})=>{
                    return (<div >
                        {
                            mode === 'EDIT' && loggedIn() && entity.operation==='RENT' &&
                            <div>
                                <RentDatePicker entity={{estate:entity.id}} t={t} mode={ArenaContainerMode.VIEW}></RentDatePicker>
                                <button className='arena-full-button' onClick={()=>history.push(`/build/container/create/rent?estate=${entity.id}&shapeName=rentCreation`)}>Crear compromiso de reserva</button>
                            </div>
                        }
                        
                    </div>)

                }
            }
        },
        10:{
            render:{
                beforeRender:({entity, t})=>(
                    <div className='title text-center'>{t(`app.backend.model.enums.EstateType.shortNames.${entity.estateType}`)} {t('lexicalConnectors.on')} {t(`app.backend.model.enums.EstateOperations.${entity.operation}`)}</div>
                ),
                afterRender: (props) =>{
                    const url=encodeURI(`${window.location.protocol}//${window.location.hostname}/build/container/view/estate/${props.entity.id}`);
                    
                    return <div className="estate-level-10-after-render mt-2">
                        <div className=' align-middle'>
                            <a className='share-button' href={`https://api.whatsapp.com/send?text=${url}`}>
                                {props.t('share', 'Compartir por ')}
                                <FontAwesomeIcon size='1x' icon={faWhatsapp} /> 
                            </a>
                        </div>
                        <LocationMapRender {...props}></LocationMapRender>
                    </div>
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
                        console.log(selectedGooglePlace);
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
                            locality: locality.long_name,
                            latitude: selectedGooglePlace.geometry.location.lat(),
                            longitude:  selectedGooglePlace.geometry.location.lng()
                        })
                    }
                    return <PlaceSelector
                        t={t}
                        update={update}
                    ></PlaceSelector>
                        
                }
            }
        }
    },
    rent:{
        default:{
            render:{
                afterRender: RentDatePicker
            }
        }
    },
    estateSearch:{
        default:{
            render:{
                beforeRender: ({mode})=>{

                    if(mode===ArenaContainerMode.CREATE){

                        // const items = [
                        //     <div className='item'>
                        //         <img src='/banner/es/1.jpg'></img>
                        //     </div>,
                        //     <div className='item'>
                        //         <img src='/banner/es/2.jpg'></img>
                        //     </div>,
                        //     <div className='item'>
                        //         <img src='/banner/es/3.jpg'></img>
                        //     </div>
                        // ]
                        return <div className='estateSearch-beforeRender'>
                            {/* <Carousel initialActiveIndex={0}showArrows={false} enableAutoPlay={true} transitionMs={1000} autoPlaySpeed={6000} itemsToShow={1} showEmptySlots={false}>
                                {items}
                            </Carousel> */}
                            <div className='title text-center'>Búsqueda</div>
                        </div>
                    }
                    return null;
                },
                afterRender:({entity = {}, updateEntity, t, mode, history})=>{
                    if(mode===ArenaContainerMode.CREATE)
                        return <div>
                            {
                                entity.operation &&
                                <div className='text-info'>El valor del precio se encuentra expresado en {entity.operation==='SALE' ? 'USD' : 'ARS'}</div>
                            }
                            <span>Ordenar por: </span>
                            <span className='btn-link' onClick={()=>updateEntity({...entity, orderDirection:"ASC"})}> Menor precio </span>
                             - 
                            <span className='btn-link' onClick={()=>updateEntity({...entity, orderDirection:"DESC"})}> Mayor precio </span>
                            {
                                entity.operation==='RENT' &&
                                <div>
                                    <RentDatePicker entity={entity} t={t} mode={ArenaContainerMode.EDIT} updateEntity={updateEntity}></RentDatePicker>
                                </div>
                            }
                        </div>
                    return null;
                }
            }
        }
    }
}
export const templatesMap = {
}
