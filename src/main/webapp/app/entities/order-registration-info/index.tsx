import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import OrderRegistrationInfo from './order-registration-info';
import OrderRegistrationInfoDetail from './order-registration-info-detail';
import OrderRegistrationInfoUpdate from './order-registration-info-update';
import OrderRegistrationInfoDeleteDialog from './order-registration-info-delete-dialog';

const OrderRegistrationInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<OrderRegistrationInfo />} />
    <Route path="new" element={<OrderRegistrationInfoUpdate />} />
    <Route path=":id">
      <Route index element={<OrderRegistrationInfoDetail />} />
      <Route path="edit" element={<OrderRegistrationInfoUpdate />} />
      <Route path="delete" element={<OrderRegistrationInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OrderRegistrationInfoRoutes;
