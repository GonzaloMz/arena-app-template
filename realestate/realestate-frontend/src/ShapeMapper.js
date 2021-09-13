import React, { useEffect, useState } from 'react';
import ReactDatePicker, { registerLocale, setHours, setMinutes } from 'react-datepicker';
import { useHistory } from "react-router-dom";
import Swal from 'sweetalert2';
import es from 'date-fns/locale/es';
import Autocomplete from "react-google-autocomplete";
import "react-datepicker/dist/react-datepicker.css";

import Camera, { FACING_MODES, IMAGE_TYPES } from '@rinse/react-html5-camera-photo';
import '@rinse/react-html5-camera-photo/build/css/index.css';

import Carousel from 'react-elastic-carousel';

registerLocale('es', es)

export const shapeConfigurationMap = {
    appointment: {
        entityRenderConfiguration: {
            allowInLineCreate: true,
            allowInLineEdit: true,
        },
        // level:3,
        onCreateFinish: (e, history) => {
            Swal.fire("Creación finalizada").then(() => { history.push('/build/listContainer/view/appointment') });
            return null
        },
        visibility: {
            visible: ['userId', 'placeId', 'estateType', 'operation', 'appointmentDate'],
            hidden: []

        },
        fields: {
            operation: {
                render: ({ update, value, t, mode }) => {
                    if (mode === 'VIEW') return false;
                    return (
                        <div style={{ textAlign: 'center' }}>
                            <div className='radio' >
                                <input label='Venta' type="radio" onClick={e => update(e.target.value)} id="SALE" value="SALE" checked={value === "SALE"}></input>
                                <input label='Alquiler' type="radio" onClick={e => update(e.target.value)} id="RENT" value="RENT" checked={value === "RENT"}></input>
                            </div>
                        </div>)
                }
            },
            appointmentDate: {
                render: (({ update, value, t, mode }) => {
                    if (mode === 'VIEW') return <DateDisplay value={value} className='appointment-date'></DateDisplay>;

                    const filterPassedTime = (time) => {
                        const currentDate = new Date();
                        const selectedDate = new Date(time);

                        return currentDate.getTime() < selectedDate.getTime();
                    };

                    return (
                        <div>
                            <div class="date-input-label">Cita</div>
                            <ReactDatePicker
                                selected={value ? new Date(value) : undefined}
                                onChange={(date) => update(date)}
                                showTimeSelect
                                timeCaption='Hora'
                                filterTime={filterPassedTime}
                                locale="es"
                                onChangeRaw={(e) => { e.preventDefault() }}
                                onCalendarOpen={(props) => console.log(props)}
                                dateFormat="MMMM d, yyyy HH:mm"
                                onFocus={(e) => e.target.readOnly = true}
                            />
                        </div>
                    );
                })
            },
            placeId: {
                visibility: {
                    visible: ['formattedAddress']
                },
                fields: {
                    formattedAddress: {
                        render: ({ update, mode, value, t }) => {
                            if (mode === 'VIEW' || mode === 'EDIT') return false;

                            return <Place update={update} value={value} t={t}/>;
                        }
                    }
                }
            }
        },
        list: {
            // shapeConfiguration:{
            onItemClick: (item, history) => {
                history.push('/build/container/view/appointment/' + item);
            },
            fields: {
                userId: {
                    // shapeConfiguration:{

                    visibility: {
                        visible: ['name', 'phone'],
                        hidden: []
                    }
                },
                placeId: {
                    visibility: {
                        visible: ['formattedAddress'],
                        hidden: []
                    }
                }
            },
            visibility: {
                visible: ['userId', 'placeId', 'estateType', 'operation'],
                hidden: []
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
    assessmentCreation: {
        entityRenderConfiguration: {
            shapeConfiguration: {

                visibility: {
                    visible: ['userId', 'placeId', 'estateType', 'operation', 'placeDescription'],

                }
            },
            allowInLineCreate: true
        },
        onCreateFinish: (e, history) => {
            Swal.fire("Creación finalizada").then(() => { history.push('/build/listContainer/view/assessment?status=ACTIVE,:,WITHOUT_DATE') });
            return null
        },
    },
    user: {
        entityRenderConfiguration: {
            shapeConfiguration: {

            }
        },
        visibility: {
            visible: ['name', 'phone', 'email'],
            hidden: []
        }
    },
    assessment: {

        entityRenderConfiguration: {

            allowInLineCreate: true,
            allowInLineEdit: true,
        },
        fields: {
            operation: {
                render: ({ update, value, t, mode }) => {
                    if (mode === 'VIEW') return false;
                    return (
                        <div style={{ textAlign: 'center' }}>
                            <div className='radio' >
                                <input label='Venta' type="radio" onClick={e => update(e.target.value)} id="SALE" value="SALE" checked={value === "SALE"}></input>
                                <input label='Alquiler' type="radio" onClick={e => update(e.target.value)} id="RENT" value="RENT" checked={value === "RENT"}></input>
                            </div>
                        </div>)
                }
            },
            placeDescription: {
                visibility: {
                    visible: ['environments', 'toilets', 'squareMeterCovered', 'squareMeterTotal', 'carPlaces', 'coveredGarage', 'laundry', 'electricity', 'gas', 'waterNetworkConnection', 'sewerConnection', 'extraNotes'],
                    hidden: []
                }
            }
        },
        list: {
            onItemClick: (item, history) => {
                history.push('/build/container/view/assessment/' + item);
            },
            visibility: {
                visible: ['userId', 'estateType', 'operation', 'placeId', 'sugestedValue'],
                hidden: []
            },
            fields: {
                userId: {
                    visibility: {
                        visible: ['name', 'phone'],
                        hidden: []
                    }
                },
                placeId: {
                    visibility: {
                        visible: ['formattedAddress'],
                        hidden: []
                    }
                },
                sugestedValue: {
                    render: ({ mode, value }) => {
                        if (mode !== 'VIEW') return false;
                        return (
                            <div class="arena-field-name-sugestedValue"><span class="arena-field-value">{new Number(value).toLocaleString()}</span></div>
                        );
                    }
                }
            }
        }
    },
    place: {


    },
    estateCreation: {
        entityRenderConfiguration: {
            allowInLineCreate: true,
            shapeConfiguration: {

                visibility: {
                    visible: ["owner", "placeId", "placeDescription", "estateType", "photos", "status"]
                },
                fields: {
                    operation: {
                        render: ({ update, value, t, mode }) => {
                            if (mode === 'VIEW') return false;
                            return (
                                <div style={{ textAlign: 'center' }}>
                                    <div className='radio' >
                                        <input label='Venta' type="radio" onClick={e => update(e.target.value)} id="SALE" value="SALE" checked={value === "SALE"}></input>
                                        <input label='Alquiler' type="radio" onClick={e => update(e.target.value)} id="RENT" value="RENT" checked={value === "RENT"}></input>
                                    </div>
                                </div>)
                        }
                    },
                    placeId: {

                        visibility: {
                            visible: ["formattedAddress"]
                        }
                    },
                    placeDescription: {
                        visibility: {
                            visible: ['environments', 'toilets', 'squareMeterCovered', 'squareMeterTotal', 'carPlaces', 'coveredGarage', 'laundry', 'electricity', 'gas', 'waterNetworkConnection', 'sewerConnection', 'extraNotes']
                        }
                    }

                }
            }
        },
        onCreateFinish: (e, history) => {
            Swal.fire("Creación finalizada").then(() => { history.push('/build/container/search/estateSearch') });
            return null
        }
    },
    estate: {
        list: {
            visibility: {
                visible: ['placeId', 'estateType', 'operation', 'photos', 'placeDescription'],
                hidden: []
            },
            fields: {
                placeId: {
                    visibility: {
                        visible: ['formattedAddress'],
                        hidden: []
                    }
                },
                photos: {
                    list: {
                        onItemClick: () => alert("foto maximizada")
                    }
                },
                placeDescription: {
                    visibility: {
                        visible: ['environments', 'carPlaces', 'squareMeterTotal', 'toilets', 'laundry', 'squareMeterCovered', 'electricity', 'gas', 'waterNetworkConnection', 'sewerConnection'],
                        hidden: []
                    }
                },
                operation: {
                    //render:({value, t})=>(<span>{`${t('operation.in')} ${value}`}</span>)
                }
            },
            onItemClick: (item, history) => {
                history.push('/build/container/view/estate/' + item);
            }
        },
        entityRenderConfiguration:{
            shapeConfiguration: {
                fields:{
                    photos:{
                        list:{
                            listRender: (items) => {
                                if (!items || items.length === 0) return null;
                                return <Carousel showArrows={true} itemsToShow={1} showEmptySlots={false}>{items.map(i => <img className='place-photo' src={i.view}></img>)}</Carousel>
                            }
                        }
                    }
                }
            }
        }
    },
    estateSearch: {
        list: {
            visibility: {
                visible: ['estate'],
                hidden: []
            },
            fields: {
                estate: {
                    props: {
                        level: 2
                    },
                    visibility: {
                        visible: ['placeId', 'estateType', 'operation', 'photos', 'placeDescription'],
                        hidden: []
                    },
                    fields: {
                        placeId: {
                            visibility: {
                                visible: ['formattedAddress'],
                                hidden: []
                            }
                        },
                        photos: {
                            list: {
                                listRender: (items) => {
                                    if (!items || items.length === 0) return null;
                                    return <Carousel showArrows={true} itemsToShow={3} showEmptySlots={false}>{items.map(i => <img className='place-photo' src={i.view}></img>)}</Carousel>
                                },
                                onItemClick: () => alert("foto maximizada")
                            }
                        },
                        placeDescription: {
                            visibility: {
                                visible: ['environments', 'carPlaces', 'squareMeterTotal', 'toilets', 'laundry', 'squareMeterCovered', 'electricity', 'gas', 'waterNetworkConnection', 'sewerConnection'],
                                hidden: []
                            }
                        }
                    }
                }
            }
        },
        fields: {
            operation: {
                render: ({ update, value, t, mode }) => {
                    if (mode === 'VIEW') return false;
                    return (
                        <div style={{ textAlign: 'center' }}>
                            <div className='radio' >
                                <input label='Venta' type="radio" onClick={e => update(e.target.value)} id="SALE" value="SALE" checked={value === "SALE"}></input>
                                <input label='Alquiler' type="radio" onClick={e => update(e.target.value)} id="RENT" value="RENT" checked={value === "RENT"}></input>
                            </div>
                        </div>)
                }
            }
        },
        onItemClick: (item, history) => {
            history.push('/build/container/view/estate/' + item);
        }
    },
    photo: {
        fields: {
            view: {
                render: ({ value, update, t }) => {
                    return (<div>
                        {
                            !value &&
                            <div>
                                <label class="custom-file-upload">

                                    <input type="file" id="image" name="image" accept="image/*" capture={false} onChange={(e) => {
                                        const imageFile = e.target.files[0];
                                        console.log(imageFile);
                                        let imageArguments, imageType, image, oldWidth, oldHeight, newHeight, canvas, ctx, newDataUrl, newWidth;

                                        // Provide default values
                                        imageType = imageType || "image/png";
                                        imageArguments = imageArguments || 0.7;

                                        // Create a temporary image so that we can compute the height of the downscaled image.
                                        image = new Image();

                                        // Create a temporary canvas to draw the downscaled image on.
                                        canvas = document.createElement("canvas");

                                        // Draw the downscaled image on the canvas and return the new data URL.
                                        ctx = canvas.getContext("2d");

                                        var reader = new FileReader();
                                        reader.onloadend = function () {
                                            console.log(reader.result)
                                            image.onload = function () {
                                                oldWidth = image.width;
                                                oldHeight = image.height;
                                                newWidth = Math.min(350, image.width);
                                                newHeight = Math.floor(oldHeight / oldWidth * newWidth)
                                                canvas.width = newWidth;
                                                canvas.height = newHeight
                                                ctx.drawImage(image, 0, 0, newWidth, newHeight);
                                                newDataUrl = canvas.toDataURL(imageType, imageArguments);
                                                update(newDataUrl)
                                            };
                                            image.src = reader.result;
                                        }
                                        reader.readAsDataURL(imageFile);

                                    }} />
                                    <div className="btn btn-primary btn-block" data-trigger="fileinput">
                                        {t("selectImageFromGallery", "Seleccionar de la galeria")}
                                    </div>
                                </label>
                                <Camera
                                    imageType={IMAGE_TYPES.JPG}
                                    imageCompression={.5}
                                    idealFacingMode={FACING_MODES.ENVIRONMENT}
                                    onTakePhoto={(dataUri) => { update(dataUri); }}>
                                </Camera>
                            </div>
                        }

                        {
                            value &&
                            <img className='place-photo' src={value}></img>
                        }
                    </div>

                    )
                }
            }
        }
    },
    owner: {

        fields: {



            address: {
                visibility: {
                    visible: ['formattedAddress']
                },
                fields: {
                    formattedAddress: {
                        render: ({ update, mode, value, t }) => {
                            if (mode === 'VIEW' || mode === 'EDIT') return false;

                            return <Place update={update} value={value} t={t}/>;
                        }
                    }
                }
            },




        }
    },
    administrator: {

        entityRenderConfiguration: {

            allowInLineCreate: true
        }
    },
    logIn: {

        entityRenderConfiguration: {

            allowInLineCreate: true
        },
        onCreateFinish: (e, history) => {
            // Swal.fire("Creación finalizada").then(() => {
            localStorage.setItem('arena-authorization', e.token); 
            localStorage.setItem('arena-administrator', e.administrator); 
            window.location.href='/';
            // });
            return null
        }
    }

}

const Place = ({ update, value, t }) => {
    const [formattedAddress, setFormattedAddress] = useState(value);
    useEffect(() => {
        update(formattedAddress);
    }, [formattedAddress])
    const updatePlace = place => {
        setFormattedAddress(place.formatted_address)
    }
    useEffect(() => {
        update(formattedAddress);
    }, [])
    const center = { lat: -36.613720, lng: -56.7048044 };
    // Create a bounding box with sides ~10km away from the center point
    const defaultBounds = {
        north: center.lat + 0.25,
        south: center.lat - 0.25,
        east: center.lng + 0.25,
        west: center.lng - 0.25
    };

    return <div>
        <div>
            <div class="arena-text place formattedAddress label">
                {t("place.formattedAddress.label")}
            </div>
        </div>    
        <Autocomplete
            apiKey={'AIzaSyBYyI_5G4yLARo3fni9u2PBKePApgXhd5U'}
            onPlaceSelected={updatePlace}
            placeholder='Ingrese una dirección'
            defaultValue={value}
            language='es_AR'
            options={{
                types: ["address"],
                componentRestrictions: { country: "ar" },
                bounds:defaultBounds
                // {
                //     north: -36.273299958870886,
                //     west: -56.615140226768425,
                //     south: -36.97482290301441,
                //     east: -56.870649405346185
                // }
                // {
                //     north: -34.969962,
                //     west: -56.36917670614324,
                //     south: -37.31999594951397,
                //     east: -56.298109887936505
                // }
                // ,
                // strictBounds:true
            }}
        />
    </div>
    ;
}

const DateDisplay = ({ value, className }) => {
    if (!value) return <div className={className}>--/--</div>
    const dateToDisplay = new Date(value);
    const dd = String(dateToDisplay.getDate()).padStart(2, '0');
    const mm = String(dateToDisplay.getMonth() + 1).padStart(2, '0'); //January is 0!
    // var yyyy = dateToDisplay.getFullYear();

    const formatted = dd + '/' + mm;

    return <div className={className}>{formatted}</div>
}