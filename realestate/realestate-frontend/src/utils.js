import React, { useEffect, useRef, useState } from 'react';
import {faCalendarAlt, faTrashAlt} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import ReactGoogleAutocomplete from 'react-google-autocomplete';

import Camera, { FACING_MODES, IMAGE_TYPES } from '@rinse/react-html5-camera-photo';
import '@rinse/react-html5-camera-photo/build/css/index.css';
import AvatarEditor from 'react-avatar-editor';

import _ from 'lodash';
import { useSelector } from 'react-redux';
import ReactDatePicker from 'react-datepicker';
import {ArenaContainerMode} from 'frontend';
import { envConfig } from './config';
import inputBuilder from './InputBuilder';

import Carousel from 'react-elastic-carousel';

export const loggedIn = ()=>(localStorage.getItem("arena-administrator") !== undefined && localStorage.getItem("arena-administrator") !== null)

export const DateDisplay = ({ value, className }) => {
    if (!value) return <div className={className}>--/--</div>
    const dateToDisplay = new Date(value);
    const dd = String(dateToDisplay.getDate()).padStart(2, '0');
    const mm = String(dateToDisplay.getMonth() + 1).padStart(2, '0'); //January is 0!
    // var yyyy = dateToDisplay.getFullYear();

    const formatted = dd + '/' + mm;

    return <div className={className}>
        <FontAwesomeIcon icon={faCalendarAlt} className='pt-2 gray-icon' size='2x'></FontAwesomeIcon>
        {formatted}
    </div>
}

export const OperationSelector = ({ update, value, t, mode }) => {
    if (mode === 'VIEW') return false;
    return (
        <div className='operation-selector'>
            <div className="subtitle">
                {t('appointment.operation.label')}
            </div>

            <div style={{ textAlign: 'center' }}>
                <div className='radio' >
                    <input label='Venta' type="radio" onClick={e => update(e.target.value)} id="SALE" value="SALE" checked={value === "SALE"}></input>
                    <input label='Alquiler' type="radio" onClick={e => update(e.target.value)} id="RENT" value="RENT" checked={value === "RENT"}></input>
                    <input label='Alquiler 36M' type="radio" onClick={e => update(e.target.value)} id="LONG_RENT" value="LONG_RENT" checked={value === "LONG_RENT"}></input>
                </div>
            </div>
        </div>)
}



export const PlaceSelector = ({ update, value='', t, updateBuilder }) => {
    const [formattedAddress, setFormattedAddress] = useState(value);
    const [selectedPlace, setSelectedPlace] = useState();
    // const [streetNumber, setStreetNumber] = useState();
    useEffect(() => {
        update(selectedPlace)
    }, [selectedPlace])
    const updatePlace = place => {
        console.log(place)
        setFormattedAddress(place.formatted_address)
        setSelectedPlace(place);
        
    }
    // useEffect(() => {
    //     update(formattedAddress);
    // }, [])
    const center = { lat: -36.613720, lng: -56.7048044 };
    // Create a bounding box with sides ~10km away from the center point
    const defaultBounds = {
        north: center.lat + 0.25,
        south: center.lat - 0.25,
        east: center.lng + 0.25,
        west: center.lng - 0.25
    };

    return <div className="autocomplete-field">
        <div>
            <div class="arena-text place formattedAddress label">
                {t("place.formattedAddress.label")}
            </div>
        </div>    
        <ReactGoogleAutocomplete
            apiKey={'AIzaSyBYyI_5G4yLARo3fni9u2PBKePApgXhd5U'}
            onPlaceSelected={(place)=>{
                updatePlace(place);
            }}
            placeholder={t("place.formattedAddress.placeholder")}
            defaultValue={""}
            value={formattedAddress}
            onFocus={()=>{
                setFormattedAddress("");
            }}
            onChange={(e)=>{
                setFormattedAddress(e.target.value);
            }}
            language='es_AR'
            className='animation_in'
            pattern="[0-9]* .*"
            options={{
                types: ["address"],
                componentRestrictions: { country: "ar" },
                bounds:defaultBounds
            }}
        />
    </div>
    ;
}


export const EditablePhotoList = (items, mapper, restart) => {
    // if (!items || items.length === 0) return null;
    const itemsToShow = !items || items.length === 0 ? [{view:"/emptyPhoto.svg"}] : items;
    return <div onClick={(e)=>e.stopPropagation()}>
            <Carousel initialActiveIndex={0}showArrows={false}   itemsToShow={1} showEmptySlots={false}>{itemsToShow.map(i => 
                <div>
                    {
                        i.id && 
                        <span style={{top:'5px'}} className='btn position-absolute background-dark-color-ligth' onClick={()=>{
                            return fetch(`${envConfig.api}/photo/${i.id}`, {
                                method: 'DELETE', // or 'PUT'
                                headers: {
                                    'Content-Type': 'application/json'
                                }
                            }).then(res => restart());
                        }}><FontAwesomeIcon icon={faTrashAlt}></FontAwesomeIcon></span>
                    }
                    <img className='place-photo' src={i.view}></img>
                </div>
                )}</Carousel>
        </div>
}


export const PhotoEditor = ({ value, update, t }) => {
    const [toCrop, setToCrop] = useState();
    const [scale, setScale] = useState(1)

    const canvas = useRef();
    const debouncedUpdate = useRef();

    const setEditorRef = (editor) => (canvas.current = editor);

    const handleScale = (e) => {
        const newScale = parseFloat(e.target.value)
        setScale(newScale);
      }
    
    return (<div>
        {
            !value && !toCrop &&
            <div>
                <label class="custom-file-upload">

                    <input type="file" id="image" name="image" accept="image/*" capture={false} onChange={(e) => {
                        const imageFile = e.target.files[0];
                        setToCrop(imageFile);

                    }} />
                    <div className="btn btn-primary btn-block" data-trigger="fileinput">
                        {t("selectImageFromGallery", "Seleccionar de la galeria")}
                    </div>
                </label>
                <Camera
                    imageType={IMAGE_TYPES.JPG}
                    imageCompression={.5}
                    idealFacingMode={FACING_MODES.ENVIRONMENT}
                    onTakePhoto={(dataUri) => { setToCrop(dataUri); }}>
                </Camera>
            </div>
        }
        { 
            toCrop && 
            <div className='image-cropper'>
                <AvatarEditor
                    ref={canvas}
                    image={toCrop}
                    width={320}
                    height={300}
                    border={5}
                    color={[255, 255, 255, 0.6]} // RGBA
                    scale={scale}
                    rotate={0}
                    onImageReady={()=>
                        update(canvas.current.getImage().toDataURL())
                    }
                    onImageChange={()=>{
                            debouncedUpdate.current && debouncedUpdate.current.cancel &&  debouncedUpdate.current.cancel();
                            debouncedUpdate.current=_.debounce( ()=>update(canvas.current.getImage().toDataURL()),100)
                        }
                    }
                />
                <div>
                    <span>
                        Zoom:
                    </span>
                    <input
                        name="scale"
                        type="range"
                        onChange={handleScale}
                        min="1"
                        max="2"
                        step="0.01"
                        defaultValue="1"
                    />
                </div>
            </div>
        }
    </div>

    )
}

export const ViewPriceRender = ({ mode, value, entity, label, className }) => {
    if (mode !== 'VIEW') return false;
    return (
        <div class={`arena-field-name-price ${className || ''}`}>
            
            <span class="arena-field-value">{label && <span>{label} </span>}{entity.currencySymbol + " " +new Number(value).toLocaleString()}</span>
        </div>
    );
}


export const LocationMapRender = ({  entity }) => {
    const place = useSelector(state=>{
        if(state.controller && state.controller.place)
            return state.controller.place[`id_${entity.placeId}`];
    })
    if(!place || !place.payload) return null;
    return (
        <div class="place-location-map">
            <div className='address'>
                <span>UBICACIÓN: </span>{place.payload.formattedAddress}
            </div>
            <img src={`https://maps.googleapis.com/maps/api/staticmap?center=${place.payload.latitude},${place.payload.longitude}&zoom=16&size=1000x400&markers=color:red|${place.payload.latitude},${place.payload.longitude}&key=AIzaSyBYyI_5G4yLARo3fni9u2PBKePApgXhd5U`}></img>
        </div>
    );
}

Date.prototype.addDays = function(days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}

export const RentDatePicker=({entity, mode, updateEntity, error, t, history})=>{
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [notAvailableDates, setNotAvailableDates] = useState(null);
    const [currentPrice, setCurrentPrice] = useState();

    useEffect(()=>{
        if(!notAvailableDates && entity.estate){
            fetch(`${envConfig.api}/rent/notAvailableDates/${entity.estate}`)
            .then(res=>res.json())
            .then(json=>setNotAvailableDates(json.map(d=>new Date(d))))
        }
        if(!entity.estate)
            setNotAvailableDates([new Date()])
    }, [])
    
    const onChange = (dates) => {
        const [start, end] = dates;
        setStartDate(start);
        setEndDate(end);
        if(!start || !end) return null;
        if(entity.estate){
            fetch(`${envConfig.api}/rent/fillPrice?estate=${entity.estate}&checkInDate=${parseDateAsParam(start)}&checkOutDate=${parseDateAsParam(end)}`)
            .then(res=>res.json())
                .then(json=>setCurrentPrice(json));
        }
        if(mode===ArenaContainerMode.VIEW)
            return null;
        updateEntity({
            ...entity,
            checkInDate:start,
            checkOutDate:end
        })


    };
    return (
        <div>
            <div className="subtitle">{t('rent.daterange.label')}</div>
            {
                currentPrice && <div className='float-right'>
                    <div><div className='underline'>{t('rent.checkInDate.label')}</div><DateDisplay value={startDate}></DateDisplay></div>
                    <div><div className='underline'>{t('rent.checkOutDate.label')}</div><DateDisplay value={endDate}></DateDisplay></div>
                    <div><div className='underline'>{t('rent.stayTotal.label')}</div>ARS {currentPrice.stayTotal}</div>
                    <div><div className='underline'>{t('rent.commission.label')}</div>ARS {currentPrice.commission}</div>
                    <div><div className='underline'>{t('rent.total.label')}</div>ARS {currentPrice.total}</div>
                    <div><div className='underline'>{t('rent.minimumPartialPayment.label')}</div>ARS {currentPrice.minimumPartialPayment}</div>
                    
                </div>
            }
            <div className='text-center'>
                <ReactDatePicker
                className="arena-edit-field"
                selected={startDate}
                onChange={onChange}
                startDate={startDate}
                endDate={endDate}
                excludeDates={notAvailableDates}
                selectsRange
                inline
                />
            </div>
        </div>
    );

}

/**
 * 
 * @param {Date} date 
 */
export const parseDateAsParam = (date) =>{
    return date.toISOString()
}

// export const PriceRangeSelector= ({ update, value, t, mode, entity, updateBuilder }) => {

//     const options = entity.operation === 'SALE' ? 
//         [
//             {value:'0,;,50000', label: t('PriceRangeSelector.usd.50000')},
//             {value:'0,;,100000', label: t('PriceRangeSelector.usd.100000')},
//             {value:'100000,;,', label: t('PriceRangeSelector.usd.50000')},
//         ] : 
//         [
//             {}
//         ]

//     const commonProps = {
//         onBlur: update,
//         value:value
//     }
//     return inputBuilder.buildSelect(commonProps, options, t('PriceRangeSelector.placeholder'))
// }