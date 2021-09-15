import React from 'react';
import {faCalendarAlt} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

export const loggedIn = ()=>(localStorage.getItem("arena-administrator") === undefined || localStorage.getItem("arena-administrator") === null)

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
                </div>
            </div>
        </div>)
}