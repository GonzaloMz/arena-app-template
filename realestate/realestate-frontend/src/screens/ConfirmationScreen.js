import React from  'react';
import {faTimesCircle} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';

export const ConfirmationScreen = ({name, componentMapper, backUrl, homeUrl="/", nextUrl}) => {
    const t = componentMapper.t;
    return <div className='text-center'>
        <Link className='float-right m-2' to={backUrl}>
            <FontAwesomeIcon size='2x' icon={faTimesCircle} /> 
        </Link><br/>
        <div className='confirmation-image'> 
            <img className='mx-5 mt-3' src={`/confirmation/${name}.svg`}></img>
        </div>
        <div className='confirmation-buttons fixed-bottom'>
            <div className='confirmation-title'>{t(`confirmation.${name}.title`, 'Creación finalizada')}</div>
            <div className='confirmation-title mb-4'>{t(`confirmation.${name}.subtitle`, 'El subtitulo')}</div>
            <Link className='arena-button btn-primary w-75 mb-2'  to={nextUrl}>
                {t(`confirmation.${name}.next`, "Siguiente")}
            </Link>
            <Link className='arena-button btn-secondary w-75' to={homeUrl}>
                {t(`confirmation.home`, 'Ir al inicio')}
            </Link>
        </div>
    </div>
}
