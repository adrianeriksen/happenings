import React from 'react';
import { Link } from 'react-router-dom';

import UserInformation from './UserInformation';
import ButtonLink from '../shared/ButtonLink';

const Header = ({ isAuthenticated, principal, deauthenticate }) => (
  <header className="top-bar">
    <h2>
      <Link to="/">Happenings</Link>
    </h2>
    {isAuthenticated ? (
      <UserInformation user={principal} deauthenticate={deauthenticate} />
    ) : (
      <div className="user-information">
        <ButtonLink to="/login">Log in</ButtonLink>
        <ButtonLink to="/signup">Sign up</ButtonLink>
      </div>
    )}
  </header>
);

export default Header;
