import React, { useState } from 'react';
import ReactDatePicker, {registerLocale, setHours, setMinutes} from 'react-datepicker';
import {useHistory} from "react-router-dom";
import Swal from 'sweetalert2';
import es from 'date-fns/locale/es';
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
                        if(mode==='VIEW') return false;

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
                                    dateFormat="MMMM d, yyyy h:mm aa"
                                    onFocus={(e)=>e.target.readOnly=true}
                                  />
                            </div>
                        );
                      })
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
            allowInLineCreate:true,
            allowInLineEdit:true,
    }
}
