import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-product-info.reducer';

export const DraftProductInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftProductInfoList = useAppSelector(state => state.draftProductInfo.entities);
  const loading = useAppSelector(state => state.draftProductInfo.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="draft-product-info-heading" data-cy="DraftProductInfoHeading">
        <Translate contentKey="tfbitaApp.draftProductInfo.home.title">Draft Product Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftProductInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/draft-product-info/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftProductInfo.home.createLabel">Create new Draft Product Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftProductInfoList && draftProductInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftProductInfo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('productAmount')}>
                  <Translate contentKey="tfbitaApp.draftProductInfo.productAmount">Product Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productAmount')} />
                </th>
                <th className="hand" onClick={sort('productDimension')}>
                  <Translate contentKey="tfbitaApp.draftProductInfo.productDimension">Product Dimension</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productDimension')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftProductInfo.product">Product</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftProductInfo.draftReceipt">Draft Receipt</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftProductInfoList.map((draftProductInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft-product-info/${draftProductInfo.id}`} color="link" size="sm">
                      {draftProductInfo.id}
                    </Button>
                  </td>
                  <td>{draftProductInfo.productAmount}</td>
                  <td>{draftProductInfo.productDimension}</td>
                  <td>
                    {draftProductInfo.product ? (
                      <Link to={`/product/${draftProductInfo.product.id}`}>{draftProductInfo.product.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftProductInfo.draftReceipt ? (
                      <Link to={`/draft-receipt/${draftProductInfo.draftReceipt.id}`}>{draftProductInfo.draftReceipt.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/draft-product-info/${draftProductInfo.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/draft-product-info/${draftProductInfo.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/draft-product-info/${draftProductInfo.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.draftProductInfo.home.notFound">No Draft Product Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftProductInfo;
