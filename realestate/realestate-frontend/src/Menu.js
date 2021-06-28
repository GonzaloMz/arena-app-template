import React from 'react';
import { Link } from 'react-router-dom'

/**
 * @param {Array} options
 */
const Menu = ({options}) => {

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light" style={{fontFamily: 'Roboto Cn'}}>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <a className="navbar-brand" href="https://gonzalomz.github.io/costoya/"><img style={{width:'80px'}} src='/LogoCostoya.svg'></img></a>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto text-left mt-3" >
                    {
                        options.map((option, i)=>{
                            return(
                                <li data-toggle="collapse" data-target="#navbarSupportedContent" key={i} className="nav-item">
                                    <Link   className="nav-link"  to={option.href}>{option.label}</Link>
                                </li>
                            )
                        })
                    }
                </ul>
            </div>
        </nav>)
}

export default Menu;