import React, { useEffect, useState } from 'react';
import ReactDatePicker, {registerLocale, setHours, setMinutes} from 'react-datepicker';
import {useHistory} from "react-router-dom";
import Swal from 'sweetalert2';
import es from 'date-fns/locale/es';
import Autocomplete from "react-google-autocomplete";
import "react-datepicker/dist/react-datepicker.css";
registerLocale('es', es)

export const shapeConfigurationMap = {
    appointment:{
        entityRenderConfiguration:{
            allowInLineCreate:true,
            allowInLineEdit:true,
        },
            // level:3,
            onCreateFinish:(e,history)=>{
                Swal.fire("Creación finalizada").then(()=>{history.push('/build/listContainer/view/appointment')});
                return null
            },
	    visibility:{
                    visible:['userId', 'placeId', 'estateType', 'operation', 'appointmentDate'],
                    hidden:[]
		
            },
            fields:{
                operation:{
                    render: ({update, value, t, mode})=>{
                        if(mode==='VIEW') return false;
                        return (
                        <div style={{textAlign:'center'}}>
                            <div className='radio' >
                                <input label='Venta' type="radio" onClick={e=>update(e.target.value)} id="SALE" value="SALE" checked={value==="SALE"}></input>
                                <input label='Alquiler' type="radio" onClick={e=>update(e.target.value)} id="RENT" value="RENT" checked={value==="RENT"}></input>
                            </div>
                        </div>)
                    }
                },
                appointmentDate:{
                    render: (({update, value, t, mode}) => {
                        if(mode==='VIEW') return <DateDisplay value={value} className='appointment-date'></DateDisplay>;

                        const filterPassedTime = (time) => {
                          const currentDate = new Date();
                          const selectedDate = new Date(time);
                      
                          return currentDate.getTime() < selectedDate.getTime();
                        };
                        
                        return (
                            <div>
                                <div class="arena-text appointment userId label">Cita</div>
                                <ReactDatePicker
                                    selected={value ? new Date(value): undefined}
                                    onChange={(date) => update(date)}
                                    showTimeSelect
                                    timeCaption='Hora'
                                    filterTime={filterPassedTime}
                                    locale="es"
                                    onChangeRaw={(e) => {e.preventDefault()}}
                                    onCalendarOpen={(props) => console.log(props)}
                                    dateFormat="MMMM d, yyyy HH:mm"
                                    onFocus={(e)=>e.target.readOnly=true}
                                  />
                            </div>
                        );
                      })
                },
                placeId: {
                    visibility:{
                        visible:['formattedAddress']
                    },
                    fields:{
                        formattedAddress:{
                            render:({update, mode, value})=>{
                                if(mode==='VIEW' || mode==='EDIT') return false;
                                
                                return <Place update={update} value={value}/>;
                            }
                        }
                    }
                }
            },
        list:{
            // shapeConfiguration:{
                onItemClick:  (item, history)=>{
                    history.push('/build/container/view/appointment/'+item);
                },
                fields:{
                    userId: {
                    // shapeConfiguration:{
    
                            visibility:{
                                visible:[ 'name','phone'],
                                hidden:[]
                            }
                        },
                    placeId: {
                        visibility:{
                            visible:['formattedAddress'],
                            hidden:[]
                        }
                    }
                },
                visibility:{
                    visible:['userId', 'placeId', 'estateType', 'operation'],
                    hidden:[]
                }
            }
        // }

        // ,fields:{
            // userId: {
                // visibility:{
                    // hidden:['name','lastName']
                // }
            // }
        // }
    // }
    },
    assessment: {
        visibility:{
            // visible:['userId', 'placeId', 'estateType', 'operation'],

        },
        entityRenderConfiguration:{

            allowInLineCreate:true,
            allowInLineEdit:true,
        },
        fields:{
            operation:{
                render: ({update, value, t, mode})=>{
                    if(mode==='VIEW') return false;
                    return (
                    <div style={{textAlign:'center'}}>
                        <div className='radio' >
                            <input label='Venta' type="radio" onClick={e=>update(e.target.value)} id="SALE" value="SALE" checked={value==="SALE"}></input>
                            <input label='Alquiler' type="radio" onClick={e=>update(e.target.value)} id="RENT" value="RENT" checked={value==="RENT"}></input>
                        </div>
                    </div>)
                }
            }
        },
        list:{
            visibility:{
                visible:['userId', 'estateType', 'operation', 'placeId', 'sugestedValue']
            },
            fields:{
                userId:{
                    visibility:{
                        visible:['name', 'phone'],
                        hidden:[]
                    }
                },
                placeId:{
                    visibility:{
                        visible:['formattedAddress'],
                        hidden:[]
                    }
                },
                sugestedValue:{
                    render:({mode, value})=> {
                        if(mode!=='VIEW') return false;
                        return (
                            <div class="arena-field-name-sugestedValue"><span class="arena-field-value">{value.toLocaleString()}</span></div>
                            );
                    }
                }
            }
        }
    },
    place:{

       
    }
}

const Place = ({update, value})=>{
    const [formattedAddress, setFormattedAddress] = useState(value);
    useEffect(()=>{
        update(formattedAddress);
    },[formattedAddress])
    const updatePlace= place=>{
        setFormattedAddress(place.formatted_address)
    }
    return <Autocomplete
        apiKey={'AIzaSyBYyI_5G4yLARo3fni9u2PBKePApgXhd5U'}
        onPlaceSelected={updatePlace}
        defaultValue={value}
        options={{
            types: ["address"],
            componentRestrictions: { country: "ar" },
          }}
        />;
}

const DateDisplay = ({value, className})=>{
    const dateToDisplay = new Date(value);
    const dd = String(dateToDisplay.getDate()).padStart(2, '0');
    const mm = String(dateToDisplay.getMonth() + 1).padStart(2, '0'); //January is 0!
    // var yyyy = dateToDisplay.getFullYear();

    const formatted = dd + '/' + mm;

    return <div className={className}>{formatted}</div>
}