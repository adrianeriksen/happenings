import React from 'react';
import { Link } from 'react-router-dom';

import Navigation from './Navigation';

const Header = ({ isAuthenticated, deauthenticate }) => (
  <header className="top-bar">
    <h2>
      <Link to="/">Happenings</Link>
    </h2>
    <Navigation
      deauthenticate={deauthenticate}
      isAuthenticated={isAuthenticated}
    />
  </header>
);

export default Header;
