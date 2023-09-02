package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.shared.domain.Service;

import java.util.List;

@Service
public class OrderInfoFetcherGateway {
    private final List<OrderInfoFetcher> orderInfoFetcher;

    public OrderInfoFetcherGateway(List<OrderInfoFetcher> orderInfoFetcher) {
        this.orderInfoFetcher = orderInfoFetcher;
    }

    public OrderInfo fetch(OrderSourceUrl orderSourceUrl) {
        return orderInfoFetcher
                .stream()
                .filter((fetcher) -> fetcher.isCompatible(orderSourceUrl))
                .findFirst()
                .orElseThrow(() -> new OrderInfoFetcherNotFoundException(orderSourceUrl))
                .fetch(orderSourceUrl);
    }
}
