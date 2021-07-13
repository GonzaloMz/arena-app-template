export const textMap = {

    es:{
	clear: "Nueva búsqueda",
        list: {
            appointment: {
                label: "Tasaciones",
                sublabel: "Pendientes"
            }
        },
        appointment: {
            create: {
                start: "Nueva Tasación",
                finish: "Agendar Tasación",
                label: "Nueva Tasación"
            },
            operation:{
                placeholder:"Operación",
                label:"Tipo de operación"
            },
            estateType:{
                placeholder:"seleccione el tipo de inmueble a tasar",
                label:"Tipo de inmueble"
            },
            placeId:{
                label: "Lugar de la tasación"
            },
            userId: {
                label: "Interesado"
            },
            appointmentDate: {
                label: "Cita"
            }
        },
        user: {
            create: {
                start: "Crear un nuevo usuario"
            },
            search: {
                inline:{
                    placeholder: "Ingrese email o teléfono"
                }
            },
            lastName:{
                label: "Apellido",
                placeholder: "Ingrese el apellido de la persona"
            },
            name:{
                label: "Nombre",
                placeholder: "Ingrese el nombre y apellido de la persona"
            },
            phone:{
                label: "Teléfono",
                placeholder: "Ingrese el teléfono de la persona"
            },
            email:{
                label: "Email",
                placeholder: "Ingrese el email de la persona"
            }
        },
        place:{
            unit:{
                label: "Departamento",
                placeholder: "Ingrese número de unidad del inmueble a tasar"
            },
            floor:{
                label: "Piso",
                placeholder: "Ingrese el piso del inmueble a tasar"
            },
            formattedAddress:{
                label: "Dirección",
                placeholder: "Ingrese la dirección de la propiedad"
            }
        },
        assessment: {
            create: {
                start: "Iniciar Tasación",
                finish: "Cerrar Tasación"
            },
            operation:{
                placeholder:"Tipo de operación",
                label:"Tipo de operación"
            },
            placeId:{
                label: "Lugar de la tasación"
            },
            userId: {
                label: "Interesado"
            },
            sugestedValue:{
                label: "Valor sugerido",
                placeholder: "Ingrese el valor sugerido para este inmueble"
            }
        },
        app:{
            backend:{
                model:{
                    enums:{
                        EstateType:{
                            CASA:'Casa',
                            DEPARTAMENTO:'Departamento', 
                            LOTE: 'Lote', 
                            LOCAL: 'Local', 
                            DUPLEX: 'Duplex', 
                            TRIPLEX: 'Triplex', 
                            COCHERA: 'Cochera', 
                            GALPON: 'Galpón',
                            null: 'No definido'
                        },
                        EstateOperations:{
                            SALE: 'Venta',
                            RENT: 'Alquiler',
                            null: 'No definido'
                        }
                    }
                }
            }
        }
    }
}
