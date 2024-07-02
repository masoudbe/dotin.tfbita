import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import OrderRegService from './order-reg-service';
import OrderRegServiceDetail from './order-reg-service-detail';
import OrderRegServiceUpdate from './order-reg-service-update';
import OrderRegServiceDeleteDialog from './order-reg-service-delete-dialog';

const OrderRegServiceRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<OrderRegService />} />
    <Route path="new" element={<OrderRegServiceUpdate />} />
    <Route path=":id">
      <Route index element={<OrderRegServiceDetail />} />
      <Route path="edit" element={<OrderRegServiceUpdate />} />
      <Route path="delete" element={<OrderRegServiceDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OrderRegServiceRoutes;
