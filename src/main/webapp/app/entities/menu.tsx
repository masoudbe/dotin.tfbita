import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/order-registration-info">
        <Translate contentKey="global.menu.entities.orderRegistrationInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/category-element">
        <Translate contentKey="global.menu.entities.categoryElement" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/custom">
        <Translate contentKey="global.menu.entities.custom" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/order-reg-serv">
        <Translate contentKey="global.menu.entities.orderRegServ" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/product">
        <Translate contentKey="global.menu.entities.product" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/licence-info">
        <Translate contentKey="global.menu.entities.licenceInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/purchase-from-other-resources">
        <Translate contentKey="global.menu.entities.purchaseFromOtherResources" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
